package com.ahmete.busbuscard.repository;

import com.ahmete.busbuscard.entity.User;
import com.ahmete.busbuscard.views.VwUser;
import com.ahmete.busbuscard.views.VwUserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findByTc(String tc);
    
    @Query("select new com.ahmete.busbuscard.views.VwUser(u.name,u.surname) from User u")
    List<VwUser> findAllUsers();
    
    @Query(value = "SELECT new com.ahmete.busbuscard.views.VwUserDetail(u.name, u.surname, u.tc, u.gender, " +
            "j.address, j.titles, c.uuid, c.balance, ce.expirationDate, c.type) " +
            "FROM User u " +
            "JOIN Jgov j ON u.id = j.userId " + // u.jgovs, User entity'sinde Jgov ile tanımlı ilişkiyi ifade eder
            "JOIN Card c ON c.id = j.cardId " +// j.cards, Jgov entity'sinde Card ile tanımlı ilişkiyi ifade eder
            "JOIN CardExpiration ce ON ce.cardId = c.id" +
            " WHERE u.tc = :tc")
    Optional<VwUserDetail> findUserDetailByTc(String tc);

}