package co.com.bancolombia.model.historialestados.gateways;

import co.com.bancolombia.model.historialestados.HistorialEstados;

public interface HistorialEstadosRepository {
  HistorialEstados save(HistorialEstados estado);
}
