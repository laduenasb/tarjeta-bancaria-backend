package com.prueba.tarjeta.Service;

import org.springframework.stereotype.Service;
import com.prueba.tarjeta.Repository.TarjetaRepository;
import com.prueba.tarjeta.Entity.TarjetaEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;

@Service
public class TarjetaService {
  private final TarjetaRepository tarjetaRepository;
  public TarjetaService(TarjetaRepository tarjetaRepository) {
    this.tarjetaRepository = tarjetaRepository;
  }
  // Guardar tarjeta
  public TarjetaEntity saveTarjeta(TarjetaEntity tarjeta) {
    return tarjetaRepository.save(tarjeta);
  }
  // Obtener tarjeta
  public TarjetaEntity getTarjeta(Long id) {
    return tarjetaRepository.findById(id).orElse(null);
  }
  // Actualizar tarjeta
  public TarjetaEntity updateTarjeta(TarjetaEntity tarjeta) {
    TarjetaEntity existingTarjeta = tarjetaRepository.findById(tarjeta.getId()).orElse(null);
    if (existingTarjeta != null) {
      existingTarjeta.setNombreTitular(tarjeta.getNombreTitular());
      existingTarjeta.setCardId(tarjeta.getCardId());
      existingTarjeta.setFechaVencimiento(tarjeta.getFechaVencimiento());
      existingTarjeta.setSaldoDisponible(tarjeta.getSaldoDisponible());
      existingTarjeta.setActiva(tarjeta.isActiva());
      return tarjetaRepository.save(existingTarjeta);
    } else {
      return null;
    }
  }
  // Eliminar tarjeta
  public void deleteTarjeta(Long id) {
    tarjetaRepository.deleteById(id);
  }

  // Generar número de tarjeta

  public String getCardNumber(Long productId) {
    // Generar los 10 dígitos aleatorios
    Random random = new Random();
    String randomDigits = String.format("%010d", random.nextInt(1000000000));

    // Combinar los 6 dígitos del productId con los dígitos aleatorios
    String cardNumber = productId + randomDigits; // Utilizar solo los últimos 10 dígitos aleatorios
    return cardNumber;
  }
  // Se crea la tarjeta y se activa la tarjeta
  public TarjetaEntity activarTarjeta(String cardId) {
    TarjetaEntity nuevaTarjeta = new TarjetaEntity();
    nuevaTarjeta.setCardId(cardId);
    nuevaTarjeta.setActiva(true);
    nuevaTarjeta.setSaldoDisponible(new BigDecimal(0));
    nuevaTarjeta.setFechaVencimiento(LocalDate.now().plusYears(3));
    return saveTarjeta(nuevaTarjeta);
  }
  // Bloquear tarjeta
  public boolean eliminarTarjeta(String cardId) {
    // Buscar la tarjeta por cardId
    Optional<TarjetaEntity> tarjetaOptional = tarjetaRepository.findByCardId(cardId);

    // Verificar si la tarjeta existe
    if (tarjetaOptional.isPresent()) {
      // Si existe, eliminar la tarjeta y retornar true
      tarjetaRepository.delete(tarjetaOptional.get());
      return true;
    } else {
      // Si no existe, retornar false
      return false;
    }
  }

  public TarjetaEntity getTarjeta(String cardId) {
    return tarjetaRepository.findByCardId(cardId).orElse(null);
  }

}
