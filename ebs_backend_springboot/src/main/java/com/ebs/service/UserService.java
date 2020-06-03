package com.ebs.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ebs.exception.BadRequestException;
import com.ebs.exception.ResourceNotFoundException;
import com.ebs.model.User;
import com.ebs.payload.ChangePasswordRequest;
import com.ebs.repository.UserRepository;
import com.ebs.security.UserPrincipal;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public void changePassword(UserPrincipal currentUser, @Valid ChangePasswordRequest changePasswordRequest) {
		String requestCurrentPassword = changePasswordRequest.getCurrentPassword();
		String encodedCurrentPassword =  currentUser.getPassword();
		boolean matches = passwordEncoder.matches(requestCurrentPassword, encodedCurrentPassword);
		if (!matches) {
			throw new BadRequestException("Current password is not correct.");
		}
		User user = userRepository.findById(currentUser.getId())
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", currentUser.getId()));
		String newEncodedPassword = passwordEncoder.encode(changePasswordRequest.getNewPassword());
		user.setPassword(newEncodedPassword);
		userRepository.save(user);
	}
}
