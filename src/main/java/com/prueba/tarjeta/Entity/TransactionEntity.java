package com.prueba.tarjeta.Entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class TransactionEntity {
  @Id
  @Column
  @GeneratedValue
  private Long transaction_id;
  @Column
  private BigDecimal price;
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "card_id", referencedColumnName = "cardId")
  private TarjetaEntity tarjeta;

}
