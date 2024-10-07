package com.ahmete.busbuscard.repository;

import com.ahmete.busbuscard.entity.Card;
import com.ahmete.busbuscard.utility.enums.ECardType;
import com.ahmete.busbuscard.utility.enums.EState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {

    int findByState(EState eState);


    int countAllByState(EState eState);

    Card findDistinctFirstByStateAndType(EState eState, ECardType eCardType);
}