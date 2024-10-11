package com.ahmete.busbuscard.repository;

import com.ahmete.busbuscard.entity.CardExpiration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CardExpirationRepository extends JpaRepository<CardExpiration, Long> {

    @Query("SELECT ce.expirationDate FROM CardExpiration ce WHERE ce.cardId = :id")
    Long getExpirationDateByCardId(Long id);

    CardExpiration findByCardId(Long id);
}
