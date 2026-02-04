package co.com.bancolombia.jpa.dao;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.Map;

@Entity
@Table(name = "activos", schema = "public")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor @Builder
public class ActivoDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_activo;

    @Column(nullable = false)
    private String tipo_activo;

    private Integer grupo;
    private Integer subgrupo;
    private Integer tipo_producto;
    private Long id_activo_padre;
    private String condicion;
    private String descripcion;
    private String compania_duena;
    private String ciudad_ubicacion;
    private String marca;

    @Column(nullable = false)
    private Integer id_estado;

//    @JdbcTypeCode(SqlTypes.JSON)
//    @Column(columnDefinition = "jsonb")
//    private List<Map<String, Object>> definicion_esquema;
//
//    @JdbcTypeCode(SqlTypes.JSON)
//    @Column(columnDefinition = "jsonb")
//    private List<Map<String, Object>> imagenes_s3;

    @Column(columnDefinition = "JSON")
    private String definicion_esquema;

    @Column(columnDefinition = "JSON")
    private String imagenes_s3;
}
