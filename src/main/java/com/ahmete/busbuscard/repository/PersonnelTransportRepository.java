package com.ahmete.busbuscard.repository;

import com.ahmete.busbuscard.entity.PersonnelTransportLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PersonnelTransportRepository extends JpaRepository<PersonnelTransportLog, Long> {

    @Query("SELECT ptl FROM PersonnelTransportLog  ptl " +
            "WHERE ptl.transportId = ?1 AND ptl.personnelCardId =?2 AND ptl.endShift = null")
    Optional<PersonnelTransportLog> findLastPersonnelTransportLog(Long transportId, Long personnelCardId);
}
