package com.ebs.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ebs.payload.AuthResponse;
import com.ebs.payload.LoginRequest;
import com.ebs.payload.SignUpRequest;
import com.ebs.service.AuthService;
import com.ebs.service.CustomerService;

/**
 * @author Poonamchand Sahu
 * 
 */

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private AuthService authService;
	@Autowired
	private CustomerService customerService;

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		String token = authService.authenticateUser(loginRequest);
		return ResponseEntity.ok(new AuthResponse(token));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
		customerService.registerUser(signUpRequest);
//		MessageResponse response = new MessageResponse("User registered successfully. Please login to continue.");
		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/users/me").build().toUri();
		return ResponseEntity.created(location).build();
	}

}
