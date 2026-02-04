package co.com.bancolombia.jpa.adapter;

import co.com.bancolombia.jpa.dao.HistorialEstadosDAO;
import co.com.bancolombia.jpa.helper.AdapterOperations;
import co.com.bancolombia.jpa.repository.IHistorialEstadosJPARepository;
import co.com.bancolombia.model.historialestados.HistorialEstados;
import co.com.bancolombia.model.historialestados.gateways.HistorialEstadosRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class HistorialEstadosAdapter
    extends AdapterOperations<HistorialEstados, HistorialEstadosDAO, Long, IHistorialEstadosJPARepository>
    implements HistorialEstadosRepository {

  public HistorialEstadosAdapter(IHistorialEstadosJPARepository repository, ObjectMapper mapper) {
    super(repository, mapper, d -> mapper.mapBuilder(d, HistorialEstados.HistorialEstadosBuilder.class).build());
  }

  @Override
  public HistorialEstados save(HistorialEstados estado) {
    HistorialEstadosDAO dao = mapper.map(estado, HistorialEstadosDAO.class);
    HistorialEstadosDAO saved = repository.save(dao);
    return mapper.map(saved, HistorialEstados.class);
  }
}
