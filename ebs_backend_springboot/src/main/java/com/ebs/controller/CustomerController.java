package com.ebs.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ebs.model.Bill;
import com.ebs.model.CustomerDetail;
import com.ebs.model.Feedback;
import com.ebs.model.Payment;
import com.ebs.payload.ChangeCustomerDetailRequest;
import com.ebs.payload.ChangePasswordRequest;
import com.ebs.payload.MessageResponse;
import com.ebs.security.CurrentUser;
import com.ebs.security.UserPrincipal;
import com.ebs.service.CustomerService;

/**
 * Contains all the features available for a registered customer
 * 
 * @author Poonamchand Sahu
 */
@RestController
@RequestMapping("/api/v1")
public class CustomerController {
	@Autowired
	private CustomerService customerService;

	/**
	 * Returns current Customer Details
	 * 
	 * @param currentUser
	 * @return CustomerDetail
	 */
	@GetMapping("/users/me")
	@PreAuthorize("hasRole('USER')")
	public CustomerDetail getCurrentUserDetails(@CurrentUser UserPrincipal currentUser) {
		return customerService.getCurrentUserDetails(currentUser.getId());
	}

	/**
	 * Updates current customer details with new details
	 * 
	 * @param currentUser
	 * @param changeCustomerDetailRequest contains new details
	 * @return CustomerDetail
	 */
	@PutMapping("/users/me")
	@PreAuthorize("hasRole('USER')")
	public CustomerDetail updateCustomerDetails(@CurrentUser UserPrincipal currentUser,
			@Valid @RequestBody ChangeCustomerDetailRequest changeCustomerDetailRequest) {
		return customerService.updateCustomerDetails(currentUser.getId(), changeCustomerDetailRequest);
	}

	/**
	 * Changes the password for current user
	 * 
	 * @param currentUser
	 * @param changePasswordRequest contains current password and new password
	 * @return
	 */
	@PutMapping("/users/change-password")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> changePassword(@CurrentUser UserPrincipal currentUser,
			@Valid @RequestBody ChangePasswordRequest changePasswordRequest) {
		customerService.changePassword(currentUser, changePasswordRequest);
		MessageResponse response = new MessageResponse(null, true);
		return ResponseEntity.ok(response);
	}

	/**
	 * Returns a bill with billId in pdf format if currentUser is authorized to
	 * access billId
	 * 
	 * @param currentUser
	 * @param billId
	 * @return
	 */
	@GetMapping("/bills/pdf/{billId}")
	@ResponseBody
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<byte[]> downloadBillpdf(@CurrentUser UserPrincipal currentUser,
			@PathVariable("billId") @NotNull(message = "Not a valid bill Id") Long billId) {
		byte[] contents = customerService.generateBillPdf(billId, currentUser.getId());
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_PDF);
		String filename = String.format("bill_%s.pdf", billId);
		headers.setContentDispositionFormData(filename, filename);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		ResponseEntity<byte[]> response = new ResponseEntity<>(contents, headers, HttpStatus.OK);
		return response;
	}

	/**
	 * Returns a bill with billId if currentUser is authorized to access billId
	 * 
	 * @param currentUser
	 * @param billId
	 * @return
	 */
	@GetMapping("/bills/{billId}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> getBillDetails(@CurrentUser UserPrincipal currentUser,
			@PathVariable("billId") @NotNull(message = "Not a valid bill Id") Long billId) {
		Bill bill = customerService.getBillDetails(billId, currentUser.getId());
		return ResponseEntity.ok(bill);
	}

	/**
	 * Returns list of all the bills for current user
	 * 
	 * @param currentUser
	 * @return
	 */
	@GetMapping("/bills")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> getAllBills(@CurrentUser UserPrincipal currentUser) {
		List<Bill> bills = customerService.getAllBills(currentUser.getId());
		return ResponseEntity.ok(bills);
	}

	/**
	 * Returns list of all the payments for current user
	 * 
	 * @param currentUser
	 * @return
	 */
	@GetMapping("/payments")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> getAllPayments(@CurrentUser UserPrincipal currentUser) {
		List<Payment> payments = customerService.getAllPayments(currentUser.getId());
		return ResponseEntity.ok(payments);
	}

	/**
	 * Takes feedback from current user
	 * 
	 * @param currentUser
	 * @param feedback
	 * @return
	 */
	@PostMapping("/users/give-feedback")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> giveFeedback(@CurrentUser UserPrincipal currentUser, @RequestBody Feedback feedback) {
		Feedback feed1 = customerService.giveFeedback(currentUser, feedback);
		return ResponseEntity.ok(feed1);
	}
}
