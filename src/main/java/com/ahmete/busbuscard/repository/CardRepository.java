package com.ahmete.busbuscard.repository;

import com.ahmete.busbuscard.entity.Card;
import com.ahmete.busbuscard.utility.enums.EState;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {

    int findByState(EState eState);
}