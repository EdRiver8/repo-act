package co.com.bancolombia.jpa.dao;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.Map;

@Entity
@Table(name = "detalles_activos", schema = "public")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor @Builder
public class DetalleActivosDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_detalle_activo;

    private Long id_activo;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private List<Map<String, Object>> datos_dinamicos;
}
