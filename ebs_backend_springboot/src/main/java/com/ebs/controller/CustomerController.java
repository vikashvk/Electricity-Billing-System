package com.ebs.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebs.model.CustomerDetail;
import com.ebs.payload.ChangePasswordRequest;
import com.ebs.payload.MessageResponse;
import com.ebs.security.CurrentUser;
import com.ebs.security.UserPrincipal;
import com.ebs.service.CustomerDetailService;
import com.ebs.service.UserService;

/**
 * @author Poonamchand Sahu
 * 
 */
@RestController
@RequestMapping("/api/v1/users")
public class CustomerController {
	@Autowired
	private CustomerDetailService customerDetailService;
	@Autowired
	private UserService userService;

	@GetMapping("/me")
	@PreAuthorize("hasRole('USER')")
	public CustomerDetail getCurrentUserDetails(@CurrentUser UserPrincipal currentUser) {
		return customerDetailService.getCurrentUserDetails(currentUser.getId());
	}

	@PutMapping("/me")
	@PreAuthorize("hasRole('USER')")
	public CustomerDetail updateCustomerDetails(@CurrentUser UserPrincipal currentUser,
			@Valid @RequestBody CustomerDetail customerDetail) {
		return customerDetailService.updateCustomerDetails(currentUser.getId(), customerDetail);
	}

	@PutMapping("/change-password")
	public ResponseEntity<?> changePassword(@CurrentUser UserPrincipal currentUser,
			@Valid @RequestBody ChangePasswordRequest changePasswordRequest) {
		userService.changePassword(currentUser, changePasswordRequest);
		MessageResponse response = new MessageResponse(null, true);
		return ResponseEntity.ok(response);
	}
}
