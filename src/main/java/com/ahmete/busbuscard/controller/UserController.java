package com.ahmete.busbuscard.controller;

import com.ahmete.busbuscard.entity.User;
import com.ahmete.busbuscard.service.UserService;
import com.ahmete.busbuscard.utility.enums.EGender;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;

	/**
	 * localhost:9090/user/register
	 */

	@PostMapping("/register")
	public void UserRegister(String name, String surname, String tc, EGender gender){
		userService.register(name, surname, tc, gender);
	}

	@GetMapping("/search")
	public User searchUser(String tc){
		Optional<User> byTC = userService.findByTC(tc);
		if(byTC.isPresent()){
			return byTC.get();
		}
		throw new NoSuchElementException("user not found");
	}

}