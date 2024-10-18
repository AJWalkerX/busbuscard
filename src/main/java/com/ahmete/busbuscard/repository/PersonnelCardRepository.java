package com.ahmete.busbuscard.repository;

import com.ahmete.busbuscard.entity.PersonnelCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonnelCardRepository extends JpaRepository<PersonnelCard, Long> {
    Optional<Long> findIdByUuid(String uuid);
}
