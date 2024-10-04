package com.ahmete.busbuscard.controller;

import com.ahmete.busbuscard.entity.User;
import com.ahmete.busbuscard.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;
	@GetMapping("/save-user-test")
	public String saveUser(){
		User user = User.builder().name("test name").build();
		userService.save(user);
		return "User saved!";
	}

	@GetMapping("/all-users")
	public List<User> findAllUsers(){
		return userService.findAll();
	}
}