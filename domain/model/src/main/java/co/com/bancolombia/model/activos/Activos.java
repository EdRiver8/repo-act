package co.com.bancolombia.model.activos;

import com.fasterxml.jackson.annotation.JsonProperty;
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
  private Object definicionEsquema;
  private Object imagenesS3;
}
