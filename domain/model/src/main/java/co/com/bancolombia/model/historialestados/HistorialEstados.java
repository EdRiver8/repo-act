package co.com.bancolombia.model.historialestados;

import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class HistorialEstados {
  private Long idHistorialEstado;
  private Long idActivo;
  private Integer idEstado;
  private LocalDateTime fecha;
  private String usuarioResponsable;
}
