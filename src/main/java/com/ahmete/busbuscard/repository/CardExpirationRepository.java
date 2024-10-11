package com.ahmete.busbuscard.repository;

import com.ahmete.busbuscard.entity.CardExpiration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardExpirationRepository extends JpaRepository<CardExpiration, Long> {
}
