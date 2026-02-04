package co.com.bancolombia.usecase.historialestados;

import co.com.bancolombia.model.historialestados.HistorialEstados;
import co.com.bancolombia.model.historialestados.gateways.HistorialEstadosRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class HistorialEstadosUseCase {
  private final HistorialEstadosRepository historialEstadosRepository;

  public HistorialEstados guardarEstado(HistorialEstados estado) {
    return historialEstadosRepository.save(estado);
  }
}
