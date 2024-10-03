package com.ahmete.busbuscard.repository;

import com.ahmete.busbuscard.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}