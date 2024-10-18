package com.ahmete.busbuscard.repository;


import com.ahmete.busbuscard.entity.Personnel;
import com.ahmete.busbuscard.utility.enums.EPersonnelType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PersonnelRepository extends JpaRepository<Personnel, Long> {

    @Query("SELECT p.ePersonnelType FROM Personnel p WHERE p.personnelCardId = ?1")
    Optional<EPersonnelType> getPersonnelTypeByCardId(Long cardId);
}
