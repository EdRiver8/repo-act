package co.com.bancolombia.jpa.adapter;

import co.com.bancolombia.jpa.dao.DetalleActivosDAO;
import co.com.bancolombia.jpa.helper.AdapterOperations;
import co.com.bancolombia.jpa.repository.IDetalleActivosJPARepository;
import co.com.bancolombia.model.detallesactivos.DetallesActivos;
import co.com.bancolombia.model.detallesactivos.gateways.DetallesActivosRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.util.logging.Logger;

@Repository
public class DetalleActivosAdapter extends AdapterOperations<DetallesActivos, DetalleActivosDAO, Long, IDetalleActivosJPARepository>
        implements DetallesActivosRepository {

    private final com.fasterxml.jackson.databind.ObjectMapper jacksonMapper = new com.fasterxml.jackson.databind.ObjectMapper();
    private static final Logger logger = Logger.getLogger(DetalleActivosAdapter.class.getName());
    
    public DetalleActivosAdapter(IDetalleActivosJPARepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.mapBuilder(d, DetallesActivos.DetallesActivosBuilder.class).build());
    }

    @Override
    public DetallesActivos save(DetallesActivos detalle) {
        DetalleActivosDAO dao = mapper.map(detalle, DetalleActivosDAO.class);
        
        // Convertir objeto JSON a String
        try {
            if (detalle.getDatosDinamicos() != null) {
                dao.setDatosDinamicos(jacksonMapper.writeValueAsString(detalle.getDatosDinamicos()));
            }
        } catch (JsonProcessingException e) {
            logger.severe("Error al serializar JSON: " + e.getMessage());
            throw new RuntimeException("Error al serializar campos JSON", e);
        }
        
        DetalleActivosDAO saved = repository.save(dao);
        return mapper.map(saved, DetallesActivos.class);
    }
}
