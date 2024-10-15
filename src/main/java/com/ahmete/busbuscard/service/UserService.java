package com.ahmete.busbuscard.service;

import com.ahmete.busbuscard.dto.request.UpdateUserRequestDto;
import com.ahmete.busbuscard.entity.User;
import com.ahmete.busbuscard.exception.BusbusCardException;
import com.ahmete.busbuscard.exception.EErrorType;
import com.ahmete.busbuscard.repository.UserRepository;
import com.ahmete.busbuscard.views.VwUser;
import com.ahmete.busbuscard.views.VwUserDetail;
import com.ahmete.mapper.UserMapper;
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

    //TODO VALIDATION_ERROR bakılacak fieldları vermiyor!
    public User findByTC(String tc) {
        Optional<User> user = userRepository.findByTc(tc);
        if(tc.length() != 11) {
            throw new BusbusCardException(EErrorType.VALIDATION_ERROR);
        }
        if(user.isEmpty()) {
            throw new BusbusCardException(EErrorType.USER_NOT_FOUND);
        }
        return user.get();
    }

    public Boolean existsByTC(String tc) {
        if (userRepository.findByTc(tc).isPresent()) {
            return true;
        }
        return false;
    }
    
    public List<VwUser> getAllUsers() {
        return userRepository.findAllUsers();
    }
    
    public Optional<VwUserDetail> getUserByTC(String tc) {
       return userRepository.findUserDetailByTc(tc);
    }

    public void updateUser(UpdateUserRequestDto dto) {
        Optional<User> user = userRepository.findByTc(dto.tc());
        if(user.isEmpty()) {
            throw new BusbusCardException(EErrorType.USER_NOT_FOUND);
        }
        userRepository.save(UserMapper.INSTANCE.fromUpdateUserRequestDto(dto, user.get()));
    }
}