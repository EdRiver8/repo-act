package co.com.bancolombia.jpa.adapter;

import co.com.bancolombia.jpa.dao.ActivoDAO;
import co.com.bancolombia.jpa.helper.AdapterOperations;
import co.com.bancolombia.jpa.repository.IActivoJPARepository;
import co.com.bancolombia.model.activos.Activos;
import co.com.bancolombia.model.activos.gateways.ActivosRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.logging.Logger;

@Repository
public class ActivoAdapter extends AdapterOperations<Activos, ActivoDAO, Long, IActivoJPARepository>
        implements ActivosRepository {

    private final ObjectMapper jacksonMapper = new ObjectMapper();
    
    public ActivoAdapter(IActivoJPARepository repository, org.reactivecommons.utils.ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.mapBuilder(d, Activos.ActivosBuilder.class).build());
    }

    private static final Logger logger = Logger.getLogger(ActivoAdapter.class.getName());

    @Override
    public List<Activos> buscarPorTipoActivo(String tipoActivo) {
        return repository.buscarPorTipoActivo(tipoActivo).stream()
                .map(dao -> mapper.map(dao, Activos.class))
                .toList();
    }

    @Override
    public List<?> findByModelo(String jsonFiltro) {
        return repository.findByModelo(jsonFiltro);
    }

    @Override
    public Activos save(Activos activo) {
        ActivoDAO dao = mapper.map(activo, ActivoDAO.class);
        ActivoDAO saved = repository.save(dao);
        return mapper.map(saved, Activos.class);
    }
}
