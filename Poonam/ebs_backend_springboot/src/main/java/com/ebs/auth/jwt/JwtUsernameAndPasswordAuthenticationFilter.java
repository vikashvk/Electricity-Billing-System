package com.ebs.auth.jwt;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;

public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private final JwtConfig jwtConfig;
	private final SecretKey secretKey;
	private final AuthenticationManager authenticationManager;

	@Autowired
	public JwtUsernameAndPasswordAuthenticationFilter(JwtConfig jwtConfig, SecretKey secretKey,
			AuthenticationManager authenticationManager) {
		super();
		this.jwtConfig = jwtConfig;
		this.secretKey = secretKey;
		this.authenticationManager = authenticationManager;
		super.setAuthenticationFailureHandler(new JWTAuthenticationFailureHandler());
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			UsernameAndPasswordAuthenticationRequest authenticationRequest = new ObjectMapper()
					.readValue(request.getInputStream(), UsernameAndPasswordAuthenticationRequest.class);
			Authentication authentication = new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
					authenticationRequest.getPassword());

			Authentication authenticate = authenticationManager.authenticate(authentication);
//			return super.attemptAuthentication(request, response);
			return authenticate;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		super.unsuccessfulAuthentication(request, response, failed);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		String token = Jwts.builder().setSubject(authResult.getName()).claim("authorities", authResult.getAuthorities())
				.setIssuedAt(new Date())
				.setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(jwtConfig.getTokenExpirationAfterDays())))
				.signWith(secretKey).compact();
		response.setContentType("application/json");
		response.getWriter().append(String.format("{\"token\":\"%s\"}", token));
	}
}
