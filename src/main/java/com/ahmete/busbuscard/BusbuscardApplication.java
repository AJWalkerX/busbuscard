package com.ahmete.busbuscard;

import com.ahmete.busbuscard.controller.UserController;
import com.ahmete.busbuscard.entity.User;
import com.ahmete.busbuscard.repository.UserRepository;
import com.ahmete.busbuscard.service.UserService;
import com.ahmete.busbuscard.utility.Runner;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@RequiredArgsConstructor
@SpringBootApplication
public class BusbuscardApplication {

	public static void main(String[] args) {
		SpringApplication.run(BusbuscardApplication.class, args);
	}
}