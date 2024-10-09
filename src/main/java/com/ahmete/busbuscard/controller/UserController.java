package com.ahmete.busbuscard.controller;

import static com.ahmete.busbuscard.constans.RestApi.*;

import com.ahmete.busbuscard.dto.response.BaseResponse;
import com.ahmete.busbuscard.entity.User;
import com.ahmete.busbuscard.service.UserService;
import com.ahmete.busbuscard.views.VwUser;
import com.ahmete.busbuscard.views.VwUserDetail;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
	@GetMapping(SEARCH)
	public User searchUser(String tc){
		Optional<User> byTC = userService.findByTC(tc);
		if(byTC.isPresent()){
			return byTC.get();
		}
		throw new NoSuchElementException("user not found");
	}
	
	@GetMapping(GETALLUSERS)
	public ResponseEntity<BaseResponse<List<VwUser>>> getAllUsers(){
		return ResponseEntity.ok(BaseResponse.<List<VwUser>>builder()
				                         .success(true)
				                         .code(200)
				                         .message("List of users")
				                         .data(userService.getAllUsers())
		                                     .build());
	}
	
	@GetMapping(GETUSER)
	public ResponseEntity<BaseResponse<VwUserDetail>> getUserDetail(String tc){
		return ResponseEntity.ok(BaseResponse.<VwUserDetail>builder()
				                         .success(true)
				                         .code(200)
				                         .message("User detail")
				                         .data(userService.getUserByTC(tc).get())
		                                     .build());
	}

}