package co.com.bancolombia.jpa.dao;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "activos", schema = "public")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActivoDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_activo")
    private Long idActivo;

    @Column(name = "tipo_activo", nullable = false)
    private String tipoActivo;

    @Column(name = "id_tipo_activo", nullable = false)
    private Integer idTipoActivo;

    private Integer grupo;
    private Integer subgrupo;

    @Column(name = "tipo_producto")
    private Integer tipoProducto;

    @Column(name = "id_activo_padre")
    private Long idActivoPadre;

    private String condicion;
    private String descripcion;

    @Column(name = "compania_duena")
    private String companiaDuena;

    @Column(name = "ciudad_ubicacion")
    private String ciudadUbicacion;

    private String marca;

    @Column(name = "id_estado", nullable = false)
    private Integer idEstado;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "definicion_esquema")
    private String definicionEsquema;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "imagenes_s3")
    private String imagenesS3;
}
