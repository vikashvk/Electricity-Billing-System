package com.ebs.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebs.payload.AuthResponse;
import com.ebs.payload.LoginRequest;
import com.ebs.payload.MessageResponse;
import com.ebs.payload.SignUpRequest;
import com.ebs.service.AuthService;

/**
 * @author Poonamchand Sahu
 * 
 */

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private AuthService authService;

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		String token = authService.authenticateUser(loginRequest);
		return ResponseEntity.ok(new AuthResponse(token));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
		URI location = authService.registerUser(signUpRequest);
		return ResponseEntity.created(location).body(new MessageResponse("User registered successfully@"));
	}

}
