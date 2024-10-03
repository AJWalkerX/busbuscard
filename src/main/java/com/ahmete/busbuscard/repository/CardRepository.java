package com.ahmete.busbuscard.repository;

import com.ahmete.busbuscard.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
}