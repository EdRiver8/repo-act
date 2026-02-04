package co.com.bancolombia.jpa.adapter;

import co.com.bancolombia.jpa.dao.DetalleActivosDAO;
import co.com.bancolombia.jpa.helper.AdapterOperations;
import co.com.bancolombia.jpa.repository.IDetalleActivosJPARepository;
import co.com.bancolombia.model.detallesactivos.DetallesActivos;
import co.com.bancolombia.model.detallesactivos.gateways.DetallesActivosRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class DetalleActivosAdapter extends AdapterOperations<DetallesActivos, DetalleActivosDAO, Long, IDetalleActivosJPARepository>
        implements DetallesActivosRepository {

    public DetalleActivosAdapter(IDetalleActivosJPARepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.mapBuilder(d, DetallesActivos.DetallesActivosBuilder.class).build());
    }

    @Override
    public DetallesActivos save(DetallesActivos detalle) {
        DetalleActivosDAO dao = mapper.map(detalle, DetalleActivosDAO.class);
        DetalleActivosDAO saved = repository.save(dao);
        return mapper.map(saved, DetallesActivos.class);
    }
}
