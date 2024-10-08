package com.ahmete.busbuscard.controller;

import static com.ahmete.busbuscard.constans.RestApi.*;

import com.ahmete.busbuscard.entity.User;
import com.ahmete.busbuscard.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;

	/**
	 * localhost:9090/user/register
	 */

	//TODO: view ekleyerek sadece gelen kullanıcıların listesi (isim-soyisim)
	@GetMapping(SEARCH)
	public User searchUser(String tc){
		Optional<User> byTC = userService.findByTC(tc);
		if(byTC.isPresent()){
			return byTC.get();
		}
		throw new NoSuchElementException("user not found");
	}

}