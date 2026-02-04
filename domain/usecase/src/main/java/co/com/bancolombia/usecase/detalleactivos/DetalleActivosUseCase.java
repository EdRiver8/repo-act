package co.com.bancolombia.usecase.detalleactivos;

import co.com.bancolombia.model.detallesactivos.DetallesActivos;
import co.com.bancolombia.model.detallesactivos.gateways.DetallesActivosRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DetalleActivosUseCase {
  private final DetallesActivosRepository detallesActivosRepository;

  public DetallesActivos guardarDetalle(DetallesActivos detalle) {
    return detallesActivosRepository.save(detalle);
  }
}
