package co.com.bancolombia.model.activos.gateways;

import co.com.bancolombia.model.activos.Activos;

import java.util.List;

public interface ActivosRepository {
    List<Activos> buscarPorTipoActivo(String tipoActivo);

    List<?> findByModelo(String jsonFiltro);

    Activos save(Activos activo);
}
