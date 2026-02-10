package co.com.bancolombia.jpa.dao;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.Map;

@Entity
@Table(name = "detalles_activos", schema = "public")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetalleActivosDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_activo")
    private Long idDetalleActivo;

    @Column(name = "id_activo")
    private Long idActivo;

    // Campo JSONB para PostgreSQL
    @Column(name = "datos_dinamicos", columnDefinition = "jsonb")
    private String datosDinamicos;
}
