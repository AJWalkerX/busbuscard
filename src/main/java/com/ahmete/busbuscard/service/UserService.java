package com.ahmete.busbuscard.service;

import com.ahmete.busbuscard.entity.User;
import com.ahmete.busbuscard.repository.UserRepository;
import com.ahmete.busbuscard.utility.enums.EGender;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

    public void save(User user) {
        userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void register(String name, String surname, String tc, EGender gender) {
        User user = User.builder().name(name).surname(surname).tc(tc).gender(gender).build();
        userRepository.save(user);
    }

    public Optional<User> findByTC(String tc) {
        return userRepository.findByTc(tc);

    }
}