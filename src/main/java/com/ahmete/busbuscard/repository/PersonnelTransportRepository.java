package com.ahmete.busbuscard.repository;

import com.ahmete.busbuscard.entity.PersonnelTransportLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonnelTransportRepository extends JpaRepository<PersonnelTransportLog, Long> {


}
