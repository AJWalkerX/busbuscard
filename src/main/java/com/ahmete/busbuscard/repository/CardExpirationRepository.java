package com.ahmete.busbuscard.repository;

import com.ahmete.busbuscard.entity.CardExpiration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CardExpirationRepository extends JpaRepository<CardExpiration, Long> {

    @Query("SELECT ce.expirationDate FROM CardExpiration ce WHERE ce.cardId = :id")
    Long getExpirationDateByCardId(Long id);
    
    @Query("SELECT ce FROM CardExpiration ce WHERE ce.cardId = :id ORDER BY ce.expirationDate DESC LIMIT 1 ")
    CardExpiration findByCardId(Long id);
}