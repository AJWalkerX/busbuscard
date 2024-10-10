package com.ahmete.busbuscard.repository;

import com.ahmete.busbuscard.entity.Card;
import com.ahmete.busbuscard.utility.enums.ECardType;
import com.ahmete.busbuscard.utility.enums.EState;
import com.ahmete.busbuscard.views.VwCardDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Long> {

    int countAllByState(EState eState);

    Card findDistinctFirstByStateAndType(EState eState, ECardType eCardType);

    @Query("select c.id from Card c where c.uuid = :cardUuid")
    Long findMyCardId(String cardUuid);

    Optional<Card> findByUuid(String cardUuid);

    boolean existsByUuid(String uuid);
    
    @Query("select new com.ahmete.busbuscard.views.VwCardDetail(c.uuid,c.balance,c.type,c.expiryDate)from Card c where c.uuid= :uuid")
    Optional<VwCardDetail> findByCardUuid(@Param("uuid") String cardUuid);
}