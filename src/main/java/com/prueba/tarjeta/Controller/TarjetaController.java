package com.prueba.tarjeta.Controller;

import com.prueba.tarjeta.Entity.TarjetaEntity;
import com.prueba.tarjeta.Service.TarjetaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/api")
public class TarjetaController {
  private final TarjetaService tarjetaService;
  public TarjetaController(TarjetaService tarjetaService) {
    this.tarjetaService = tarjetaService;
  }
  // Generar número de tarjeta
  // Tipo de método: GET
  // Recurso: /card/{productId}/number
  @GetMapping("/card/{productId}/number")
  public String getCardNumber(@PathVariable Long productId) {
    return tarjetaService.getCardNumber(productId);
  }

  // Activar tarjeta
  // Tipo de método: POST
  // Recurso: /card/enroll
  @PostMapping("/card/enroll")
  public ResponseEntity<?> activarTarjeta(@RequestBody Map<String, String> requestBody) {
    String cardId = requestBody.get("cardId");
    if (cardId == null || cardId.isEmpty()) {
      return ResponseEntity.badRequest().body("El campo 'cardId' es requerido");
    }

    TarjetaEntity tarjetaActivada = tarjetaService.activarTarjeta(cardId);
    if (tarjetaActivada == null) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al activar la tarjeta");
    }

    return ResponseEntity.ok().body("Tarjeta activada correctamente");
  }
  // Bloquear tarjeta
  // Tipo de método: DELETE
  // Recurso: /card/{cardId}
  @DeleteMapping("/card/{cardId}")
  public ResponseEntity<?> bloquearTarjeta(@PathVariable String cardId) {
    boolean eliminada = tarjetaService.eliminarTarjeta(cardId);
    return (eliminada) ? ResponseEntity.ok().body("Tarjeta bloqueada correctamente") : ResponseEntity.ok().body("Tarjeta no encontrada");
  }
  /*
  public boolean bloquearTarjeta(@PathVariable String cardId) {
    return tarjetaService.eliminarTarjeta(cardId);
  }
  */
  // Obtener tarjeta por cardId
  @GetMapping("/card/{cardId}")
  public TarjetaEntity getTarjeta(@PathVariable String cardId) {
    return tarjetaService.getTarjeta(cardId);
  }
}
