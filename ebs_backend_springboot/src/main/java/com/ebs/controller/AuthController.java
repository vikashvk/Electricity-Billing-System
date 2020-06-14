package com.ebs.controller;

import java.net.URI;

import javax.validation.Valid;
import javax.validation.constraints.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ebs.payload.AuthResponse;
import com.ebs.payload.LoginRequest;
import com.ebs.payload.PasswordResetRequest;
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

//logs the user in
	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		String token = authService.authenticateUser(loginRequest);
		return ResponseEntity.ok(new AuthResponse(token));
	}

//registers the user
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
		customerService.registerUser(signUpRequest);
		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/users/me").build().toUri();
		return ResponseEntity.created(location).build();
	}

//verifies email based on token 
	@GetMapping("/verify-email")
	public ResponseEntity<?> verifyEmail(@RequestParam("token") String token) {
		String loginUrl = "http://localhost:4200/login";
		customerService.verifyEmail(token);
		String content = "<p>Email verified successfully. <a href=\"" + loginUrl + "\">Click Here to login</a>";
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.TEXT_HTML);
		return new ResponseEntity<String>(content, responseHeaders, HttpStatus.OK);
	}

//sends password reset email
	@GetMapping("/get-password-reset-token")
	public ResponseEntity<?> getPasswordResetToken(@Email(message = "Not a valid email") @RequestParam String email) {
		customerService.getPasswordResetToken(email);
		return ResponseEntity.ok().build();
	}

	// Resets the password
	@PutMapping("/reset-password")
	public ResponseEntity<?> resetPassword(@Valid @RequestBody PasswordResetRequest passwordResetRequest) {
		customerService.resetPassword(passwordResetRequest);
		return ResponseEntity.ok().build();
	}
}
