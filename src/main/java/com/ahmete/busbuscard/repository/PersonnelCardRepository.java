package com.ahmete.busbuscard.repository;

import com.ahmete.busbuscard.entity.PersonnelCard;
import com.ahmete.busbuscard.utility.enums.ECardStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PersonnelCardRepository extends JpaRepository<PersonnelCard, Long> {
  @Query("SELECT pc.id FROM PersonnelCard pc WHERE pc.uuid=?1")
    Optional<Long> findIdByUuid(String uuid);

  @Query("SELECT pc.eCardStatus FROM PersonnelCard pc WHERE pc.uuid =?1")
  ECardStatus findCardStatusByUuid(String uuid);


  Optional<PersonnelCard> findByUuid(String uuid);
}