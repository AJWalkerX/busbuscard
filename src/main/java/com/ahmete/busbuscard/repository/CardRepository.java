package com.ahmete.busbuscard.repository;

import com.ahmete.busbuscard.entity.Card;
import com.ahmete.busbuscard.utility.enums.ECardType;
import com.ahmete.busbuscard.utility.enums.EState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Long> {

    int countAllByState(EState eState);

    Card findDistinctFirstByStateAndType(EState eState, ECardType eCardType);

    Optional<Card> findByUuid(String cardUuid);
}