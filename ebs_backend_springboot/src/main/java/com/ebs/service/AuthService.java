package com.ebs.service;

import java.net.URI;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ebs.exception.BadRequestException;
import com.ebs.model.AuthProvider;
import com.ebs.model.CustomerDetail;
import com.ebs.model.User;
import com.ebs.payload.LoginRequest;
import com.ebs.payload.SignUpRequest;
import com.ebs.repository.CustomerDetailRespository;
import com.ebs.repository.UserRepository;
import com.ebs.security.TokenProvider;

@Service
public class AuthService {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CustomerDetailRespository customerDetailRespository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private TokenProvider tokenProvider;

	public String authenticateUser(LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = tokenProvider.createToken(authentication);
		return token;
	}

	public URI registerUser(SignUpRequest signUpRequest) {
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			throw new BadRequestException("Email address already in use.");
		}
		Date now = new Date();

		// Creating user's account
		User user = new User();
		CustomerDetail customerDetail = new CustomerDetail();
		user.setEmail(signUpRequest.getEmail());
		user.setPassword(signUpRequest.getPassword());
		user.setProvider(AuthProvider.local);
		user.setUpdatedAt(now);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		customerDetail.setFirstName(signUpRequest.getFirstName());
		customerDetail.setLastName(signUpRequest.getLastName());
		customerDetail.setUpdatedAt(now);
		customerDetail.setUser(user);
		CustomerDetail result = customerDetailRespository.save(customerDetail);

		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/me")
				.buildAndExpand(result.getId()).toUri();

		return location;
	}
}
