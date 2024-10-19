package com.ahmete.busbuscard.repository;

import com.ahmete.busbuscard.entity.Transport;
import com.ahmete.busbuscard.utility.enums.ETransportState;
import com.ahmete.busbuscard.views.VwTransport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface TransportRepository extends JpaRepository<Transport, Integer> {

    @Query("SELECT new com.ahmete.busbuscard.views.VwTransport(t.id,t.eTransportType, t.eTransportState) " +
            "FROM Transport t WHERE t.plateNo = ?1")
    Optional<VwTransport> findByPlateNo(String plateNo);

    Transport findById(Long id);

    @Query("SELECT t.eTransportState FROM Transport t WHERE t.id = ?1")
    ETransportState findStatusById(Long id);


    @Query("SELECT t FROM Transport  t WHERE  t.plateNo = ?1")
    Optional<Long> findIdByPlateNo(String plate);
}