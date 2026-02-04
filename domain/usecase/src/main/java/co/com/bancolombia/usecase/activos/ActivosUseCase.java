package co.com.bancolombia.usecase.activos;

import co.com.bancolombia.model.activos.Activos;
import co.com.bancolombia.model.activos.gateways.ActivosRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ActivosUseCase {
  private final ActivosRepository activosRepository;

  public List<Activos> buscarPorTipoActivo(String tipoActivo) {
    return activosRepository.buscarPorTipoActivo(tipoActivo);
  }

  public List<?> buscarPorModelo(String jsonFiltro) {
    return activosRepository.findByModelo(jsonFiltro);
  }

  public Activos guardarActivo(Activos activo) {
    return activosRepository.save(activo);
  }
}
