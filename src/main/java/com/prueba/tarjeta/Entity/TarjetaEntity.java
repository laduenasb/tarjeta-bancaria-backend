package com.prueba.tarjeta.Entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class TarjetaEntity {
  @Id
  @Column
  @GeneratedValue
  private Long Id;
  @Column
  private String nombreTitular;
  @Column(length = 16, unique = true)
  private String cardId;
  @Column
  private LocalDate fechaVencimiento;
  @Column(precision = 10, scale = 2)
  private BigDecimal saldoDisponible;
  @Column
  private boolean activa;

  public Long getId() {
    return Id;
  }

  public void setId(Long id) {
    Id = id;
  }

  public String getNombreTitular() {
    return nombreTitular;
  }

  public void setNombreTitular(String nombreTitular) {
    this.nombreTitular = nombreTitular;
  }

  public String getCardId() {
    return cardId;
  }

  public void setCardId(String cardId) {
    this.cardId = cardId;
  }

  public LocalDate getFechaVencimiento() {
    return fechaVencimiento;
  }

  public void setFechaVencimiento(LocalDate fechaVencimiento) {
    this.fechaVencimiento = fechaVencimiento;
  }

  public BigDecimal getSaldoDisponible() {
    return saldoDisponible;
  }

  public void setSaldoDisponible(BigDecimal saldoDisponible) {
    this.saldoDisponible = saldoDisponible;
  }

  public boolean isActiva() {
    return activa;
  }

  public void setActiva(boolean activa) {
    this.activa = activa;
  }
}
