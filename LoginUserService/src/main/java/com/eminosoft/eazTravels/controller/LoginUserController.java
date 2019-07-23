package com.eminosoft.eazTravels.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eminosoft.eazTravels.model.User;
import com.eminosoft.eazTravels.service.UserService;
import com.eminosoft.eazTravels.util.LoginResponse;

@RestController
@RequestMapping("/User")
public class LoginUserController {

	@Autowired
	private UserService userService;

	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody User user) {
		LoginResponse response = new LoginResponse();	

		LoginResponse result =	userService.login(user.getEmail(),user.getPassword());
		if(result.isStatus()==true) {

			response.setUserName(result.getUserName());
			response.setEmail(result.getEmail());
			response.setMessage(result.getMessage());
			response.setStatus(result.isStatus());
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		else {
			response.setMessage(result.getMessage());
			
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

		}

	}
}
