package com.prueba.tarjeta.Repository;

import com.prueba.tarjeta.Entity.TarjetaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TarjetaRepository extends JpaRepository<TarjetaEntity, Long> {
//  void deleteBycardId(String cardId);
  Optional<TarjetaEntity> findByCardId(String cardId);
}
