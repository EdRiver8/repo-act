package co.com.bancolombia.jpa.config;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserCNXSecret {

    private final String host;

    private final String port;

    private final String dbname;

    private final String username;

    private final String password;

    private final String engine;

    private final String dbInstanceIdentifier;
}
