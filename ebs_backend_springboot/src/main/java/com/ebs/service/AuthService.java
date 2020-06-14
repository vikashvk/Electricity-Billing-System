package com.ebs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ebs.payload.LoginRequest;
import com.ebs.security.TokenProvider;

/**
 * Provides Authentication related features
 * 
 * @author Poonamchand Sahu
 *
 */
@Service
public class AuthService {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenProvider tokenProvider;

	/**
	 * Gets the credentials form loginRequest and logs in the user
	 * 
	 * @param loginRequest
	 * @return String JWT
	 */
	public String authenticateUser(LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = tokenProvider.createToken(authentication);
		return token;
	}
}
