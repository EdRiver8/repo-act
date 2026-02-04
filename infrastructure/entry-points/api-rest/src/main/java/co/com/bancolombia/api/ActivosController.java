package co.com.bancolombia.api;

import co.com.bancolombia.model.activos.Activos;
import co.com.bancolombia.model.detallesactivos.DetallesActivos;
import co.com.bancolombia.model.historialestados.HistorialEstados;
import co.com.bancolombia.usecase.activos.ActivosUseCase;
import co.com.bancolombia.usecase.detalleactivos.DetalleActivosUseCase;
import co.com.bancolombia.usecase.historialestados.HistorialEstadosUseCase;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/activos", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ActivosController {

  private final ActivosUseCase activosUseCase;
  private final HistorialEstadosUseCase historialEstadosUseCase;
  private final DetalleActivosUseCase detalleActivosUseCase;

  @GetMapping("/tipo")
  public ResponseEntity<List<Activos>> getByTipoActivo(@RequestParam("tipo_activo") String tipo_activo) {
    log.info("Tipo Activo recibido: {}", tipo_activo);
    List<Activos> result = activosUseCase.buscarPorTipoActivo(tipo_activo);
    return ResponseEntity.ok(result);
  }

//  @GetMapping("/modelo")
//  public ResponseEntity<List<?>> getByModelo(@RequestBody JsonNode modelo) {
//    String jsonFiltro = modelo.toString();
//    log.info("Modelo recibido: {}", jsonFiltro);
//    List<?> result = activosUseCase.buscarPorModelo(jsonFiltro);
//    return ResponseEntity.ok(result);
//  }

  @GetMapping("/modelo")
  public ResponseEntity<List<?>> getByModelo(@RequestBody String modelo) {
    log.info("Modelo recibido: {}", modelo);
    List<?> result = activosUseCase.buscarPorModelo(modelo);
    return ResponseEntity.ok(result);
  }

  @PostMapping("/save")
  public ResponseEntity<String> save(@RequestBody Activos activo) {
    log.info("Guardando activo: {}", activo);
    // Asignar estado por defecto si no viene en la petición
    if (activo.getIdEstado() == null) {
      activo.setIdEstado(1); // Estado por defecto: Activo
    }
    activosUseCase.guardarActivo(activo);
    return ResponseEntity.status(HttpStatus.CREATED).body("guardado");
  }

  @PostMapping("/guardarEstado")
  public ResponseEntity<String> saveEstado(@RequestBody HistorialEstados estado) {
    log.info("Guardando estado: {}", estado);
    historialEstadosUseCase.guardarEstado(estado);
    return ResponseEntity.status(HttpStatus.CREATED).body("Estado guardado");
  }

  @PostMapping("/guardarDatos")
  public ResponseEntity<String> saveDatos(@RequestBody DetallesActivos detalle) {
    log.info("Guardando detalle: {}", detalle);
    detalleActivosUseCase.guardarDetalle(detalle);
    return ResponseEntity.status(HttpStatus.CREATED).body("Detalle guardado");
  }
}
