package co.com.bancolombia.model.activos;

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
public class Activos {
  private Long idActivo;
  private String tipoActivo;
  private Integer grupo;
  private Integer subgrupo;
  private Integer tipoProducto;
  private Long idActivoPadre;
  private String condicion;
  private String descripcion;
  private String companiaDuena;
  private String ciudadUbicacion;
  private String marca;
  private Integer idEstado;
  private List<Map<String, Object>> definicionEsquema;
  private List<Map<String, Object>> imagenesS3;
}
