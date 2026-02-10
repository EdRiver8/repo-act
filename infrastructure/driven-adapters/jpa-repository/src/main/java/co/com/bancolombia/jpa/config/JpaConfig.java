package co.com.bancolombia.jpa.config;

import co.com.bancolombia.jpa.exception.DataSourceCreationException;
import co.com.bancolombia.jpa.exception.SecretRetrievalException;
import co.com.bancolombia.secretsmanager.api.GenericManager;
import co.com.bancolombia.secretsmanager.api.exceptions.SecretException;
import co.com.bancolombia.secretsmanager.connector.AWSSecretManagerConnector;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@Slf4j
public class JpaConfig {

    private final String env1;

    private final String secretName;

    private final String region;

    // default value, se podria poner cualquier cosa para que no falle, esta no se
    // usa en local
    private final String startUrl;

    public JpaConfig(@Value("${spring.profiles.active}") String env1, @Value("${aws.db.secretName}") String secretName,
                     @Value("${aws.db.region}") String region, @Value("${aws.db.start-url:default-value}") String startUrl) {
        this.env1 = env1;
        this.secretName = secretName;
        this.region = region;
        this.startUrl = startUrl;
    }

    /**
     * Get the secret from AWS Secret Manager and create a DBSecret object.
     *
     * @param env The environment object.
     * @return The DBSecret object.
     */
    @Bean
    public DBSecret dbSecret(Environment env) {
        try {
            if ("local".equals(env1)) {
                return DBSecret.builder()
                        .url(env.getProperty("spring.datasource.url"))
                        .username(env.getProperty("spring.datasource.username"))
                        .password(env.getProperty("spring.datasource.password"))
                        .build();
            } else {
                log.info("Intentado obtener secreto de AWS");
                GenericManager connector = new AWSSecretManagerConnector(region);
                UserCNXSecret userCNXSecret = connector.getSecret(secretName, UserCNXSecret.class);
                return DBSecret.builder()
                        .url(startUrl + userCNXSecret.getHost() + ":"
                                + userCNXSecret.getPort() + "/" + userCNXSecret.getDbname())
                        .username(userCNXSecret.getUsername())
                        .password(userCNXSecret.getPassword())
                        .build();
            }
        } catch (SecretException e) {
            throw new SecretRetrievalException("Fallo al obtener el secreto de AWS para la DB", e);
        }
    }

    /**
     * Create a DataSource object.
     *
     * @param secret      The DBSecret object.
     * @param driverClass The driver class name.
     * @return The DataSource object.
     */
    @Bean
    public DataSource datasource(DBSecret secret, @Value("${spring.datasource.driverClassName}") String driverClass) {
        try {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(secret.getUrl());
            config.setUsername(secret.getUsername());
            config.setPassword(secret.getPassword());
            config.setDriverClassName(driverClass);
            return new HikariDataSource(config);
        } catch (Exception e) {
            // Wrap any exception (including runtime exceptions from Hikari) into a
            // domain-specific checked exception
            throw new DataSourceCreationException("Fallo al crear el DataSource de la DB", e);
        }
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("co.com.bancolombia.jpa");

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        return em;
    }
}
