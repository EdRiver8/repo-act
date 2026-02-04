package co.com.bancolombia.jpa.repository;

import co.com.bancolombia.jpa.dao.ActivoDAO;
import co.com.bancolombia.jpa.dao.DetalleActivosDAO;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;

public interface IActivoJPARepository extends JpaRepository<ActivoDAO, Long>, QueryByExampleExecutor<ActivoDAO> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO activos(" +
            "tipo_activo, grupo, subgrupo, tipo_producto, id_activo_padre, condicion, descripcion, compania_duena, ciudad_ubicacion, marca, id_estado, definicion_esquema, imagenes_s3) " +
            "VALUES (:tipo_activo, :grupo, :subgrupo, :tipo_producto, :id_activo_padre, :condicion, :descripcion, :compania_duena, :ciudad_ubicacion, :marca, :id_estado, " +
            ":definicion_esquema, :imagenes_s3)", nativeQuery = true)
    void insertarNativo(
            String tipo_activo,
            Integer grupo,
            Integer subgrupo,
            Integer tipo_producto,
            Long id_activo_padre,
            String condicion,
            String descripcion,
            String compania_duena,
            String ciudad_ubicacion,
            String marca,
            Integer id_estado,
            Object definicion_esquema,
            Object imagenes_s3
    );

    @Query(
            value = "SELECT * FROM activos WHERE tipo_activo = :tipo_activo",
            nativeQuery = true
    )
    List<ActivoDAO> buscarPorTipoActivo(@Param("tipo_activo") String tipoActivo);


    @Query(
            value = "SELECT da.* " +
                    "FROM detalles_activos da " +
                    "WHERE CAST(da.datos_dinamicos AS VARCHAR) LIKE CONCAT('%', :jsonFiltro, '%')",
            nativeQuery = true
    )
    List<DetalleActivosDAO> findByModelo(@Param("jsonFiltro") String jsonFiltro);
}
