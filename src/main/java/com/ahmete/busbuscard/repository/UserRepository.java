package com.ahmete.busbuscard.repository;

import com.ahmete.busbuscard.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findByTc(String tc);
}