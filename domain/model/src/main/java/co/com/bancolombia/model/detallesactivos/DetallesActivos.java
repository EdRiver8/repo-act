package co.com.bancolombia.model.detallesactivos;

import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class DetallesActivos {
  private Long idDetalleActivo;
  private Long idActivo;
  private List<Map<String, Object>> datosDinamicos;
}
