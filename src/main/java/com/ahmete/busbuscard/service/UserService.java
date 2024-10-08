package com.ahmete.busbuscard.service;

import com.ahmete.busbuscard.entity.User;
import com.ahmete.busbuscard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);

    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findByTC(String tc) {
        return userRepository.findByTc(tc);

    }

    public Boolean existsByTC(String tc) {
        if (userRepository.findByTc(tc).isPresent()) {
            return true;
        }
        return false;

    }
}