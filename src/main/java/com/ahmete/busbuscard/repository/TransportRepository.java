package com.ahmete.busbuscard.repository;

import com.ahmete.busbuscard.entity.Transport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransportRepository extends JpaRepository<Transport, Integer> {
}