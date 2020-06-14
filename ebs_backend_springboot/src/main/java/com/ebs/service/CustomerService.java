package com.ebs.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ebs.config.AppProperties;
import com.ebs.exception.BadRequestException;
import com.ebs.exception.ResourceNotFoundException;
import com.ebs.model.Address;
import com.ebs.model.AuthProvider;
import com.ebs.model.Bill;
import com.ebs.model.ConfirmationToken;
import com.ebs.model.CustomerDetail;
import com.ebs.model.Feedback;
import com.ebs.model.Payment;
import com.ebs.model.User;
import com.ebs.payload.ChangeCustomerDetailRequest;
import com.ebs.payload.ChangePasswordRequest;
import com.ebs.payload.PasswordResetRequest;
import com.ebs.payload.SignUpRequest;
import com.ebs.repository.AddressRepository;
import com.ebs.repository.BillDAO;
import com.ebs.repository.ConfirmationTokenRepository;
import com.ebs.repository.CustomerDetailRespository;
import com.ebs.repository.FeedbackDao;
import com.ebs.repository.PaymentRepository;
import com.ebs.repository.UserRepository;
import com.ebs.security.UserPrincipal;
import com.ebs.util.PdfUtils;

/**
 * Provides Customer related features
 * 
 * @author Poonamchand Sahu
 *
 */
@Service
public class CustomerService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private CustomerDetailRespository customerDetailRespository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private BillDAO billDao;
	@Autowired
	private FeedbackDao feedbackDao;
	@Autowired
	private PaymentRepository paymentRepository;
	@Autowired
	private EmailSenderService emailSenderService;
	@Autowired
	private ConfirmationTokenRepository tokenRepository;
	@Autowired
	private AppProperties appProperties;

	/**
	 * Returns customer detais for specific userId
	 * 
	 * @param userId
	 * @return CustomerDetail
	 * @throws if no user is found with provided userId
	 */
	public CustomerDetail getCurrentUserDetails(Long userId) {
		return customerDetailRespository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
	}

	/**
	 * Gets the user details from signUpRequest and registers the user
	 * 
	 * @param signUpRequest
	 */
	public void registerUser(SignUpRequest signUpRequest) {
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			throw new BadRequestException("Email address already in use.");
		}

		Date now = new Date();
		// Address
		Address address = new Address();
		address.setUpdatedAt(now);
		Address returnedAddress = addressRepository.save(address);
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
		customerDetail.setAddress(returnedAddress);
		customerDetail.setUpdatedAt(now);
		CustomerDetail registeredCustomer = customerDetailRespository.save(customerDetail);
		ConfirmationToken confirmationToken = new ConfirmationToken(registeredCustomer.getCustId());
		sendVerificationMail(signUpRequest.getEmail(), confirmationToken.getConfirmationToken(),
				registeredCustomer.getFirstName());
		tokenRepository.save(confirmationToken);

	}

	/**
	 * Update customer details for provided userId and returns updated details
	 * 
	 * @param userId
	 * @param changeCustomerDetailRequest
	 * @return CustomerDetail
	 */
	public CustomerDetail updateCustomerDetails(Long userId, ChangeCustomerDetailRequest changeCustomerDetailRequest) {
		CustomerDetail customerDetail = customerDetailRespository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		Date now = new Date();
		Address address = customerDetail.getAddress();
		address.setCity(changeCustomerDetailRequest.getCity());
		address.setCountry(changeCustomerDetailRequest.getCountry());
		address.setLine1(changeCustomerDetailRequest.getLine1());
		address.setLine2(changeCustomerDetailRequest.getLine2());
		address.setState(changeCustomerDetailRequest.getState());
		address.setPincode(changeCustomerDetailRequest.getPincode());
		address.setUpdatedAt(now);
		customerDetail.setFirstName(changeCustomerDetailRequest.getFirstName());
		customerDetail.setLastName(changeCustomerDetailRequest.getLastName());
		customerDetail.setMobile(changeCustomerDetailRequest.getMobile());
		customerDetail.setAddress(address);
		customerDetail.setUpdatedAt(now);
		return customerDetailRespository.save(customerDetail);
	}

	/**
	 * Changes current User password
	 * 
	 * @param currentUser
	 * @param changePasswordRequest
	 */
	public void changePassword(UserPrincipal currentUser, ChangePasswordRequest changePasswordRequest) {
		String requestCurrentPassword = changePasswordRequest.getCurrentPassword();
		String requestNewPassword = changePasswordRequest.getNewPassword();
		String encodedCurrentPassword = currentUser.getPassword();
		boolean matches = passwordEncoder.matches(requestCurrentPassword, encodedCurrentPassword);
		if (!matches) {
			throw new BadRequestException("Incorrect password");// If current password and entered current password do
																// not match
		}
		User user = userRepository.findById(currentUser.getId())
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", currentUser.getId()));
		if (requestCurrentPassword.equals(requestNewPassword)) {
			throw new BadRequestException("New password cannot be same as old password");// If current password and new
																							// password are same
		}
		String newEncodedPassword = passwordEncoder.encode(requestNewPassword);
		user.setPassword(newEncodedPassword);
		userRepository.save(user);
	}

	/**
	 * Verifies the email
	 * 
	 * @param token
	 * @throws BadRequestException if provided token is invalid else sets
	 *                             emailVerified of User to true
	 */
	public void verifyEmail(String token) {
		Date today = new Date();
		String invalidTokenMessage = "Invalid token";
		BadRequestException badRequestException = new BadRequestException(invalidTokenMessage);
		if (token.isEmpty() || token == null) {
			throw badRequestException;
		}
		ConfirmationToken confirmationToken = tokenRepository.findByConfirmationToken(token)
				.orElseThrow(() -> badRequestException);
		if (confirmationToken.getExpirationTime().after(today)) {
			Long custId = confirmationToken.getCustId();
			User user = userRepository.getOne(custId);
			user.setEmailVerified(true);
			userRepository.save(user);
			tokenRepository.delete(confirmationToken);
		} else {
			throw badRequestException;
		}
	}

	/**
	 * Generates password reset token and sends email
	 * 
	 * @param email
	 * @throws ResourceNotFoundException if user not found with provided email
	 */
	public void getPasswordResetToken(String email) {
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
		ConfirmationToken confirmationToken = new ConfirmationToken(user.getCustId());
		sendPasswordResetToken(email, confirmationToken.getConfirmationToken());
		tokenRepository.save(confirmationToken);
	}

	/**
	 * Matches the token and sets new password
	 * 
	 * @param passwordResetRequest
	 * @return void
	 * @throws BadRequestException if token is invalid or expired.
	 * 
	 */
	public void resetPassword(PasswordResetRequest passwordResetRequest) {
		String token = passwordResetRequest.getToken();

		Date today = new Date();
		String invalidTokenMessage = "Invalid token";
		BadRequestException badRequestException = new BadRequestException(invalidTokenMessage);
		if (token.isEmpty() || token == null) {
			throw badRequestException;
		}
		ConfirmationToken confirmationToken = tokenRepository.findByConfirmationToken(token)
				.orElseThrow(() -> badRequestException);
		if (confirmationToken.getExpirationTime().after(today)) {
			String encodedPassword = passwordEncoder.encode(passwordResetRequest.getNewPassword());
			Long custId = confirmationToken.getCustId();
			User user = userRepository.getOne(custId);
			user.setPassword(encodedPassword);
			userRepository.save(user);
			tokenRepository.delete(confirmationToken);
		} else {
			throw badRequestException;
		}

	}

	/**
	 * 
	 * Returns Bill of billId
	 * 
	 * @param billId
	 * @param custId
	 * @return Bill of billId
	 * @throws BadRequestException       if user is not authorized to access bill of
	 *                                   billId
	 * @throws ResourceNotFoundException if bill of billId is not found
	 */
	public Bill getBillDetails(Long billId, Long custId) {
		Bill bill = billDao.findById(billId).orElseThrow(() -> new ResourceNotFoundException("Bill", "id", billId));
		System.out.println(bill.getCustomerid());
		System.out.println(custId);
		if (!bill.getCustomerid().equals(custId)) {
			throw new BadRequestException("You not are authorized to access this resource");
		}
		return bill;
	}

	/**
	 * Generates bill pdf
	 * 
	 * @param billId
	 * @param custId
	 * @return Byte[] of Bill Pdf
	 * @throws BadRequestException if IOException occurs
	 */
	public byte[] generateBillPdf(Long billId, Long custId) {
		Bill bill = getBillDetails(billId, custId);
		CustomerDetail customer = getCurrentUserDetails(custId);
		byte[] contents;
		try {
			contents = PdfUtils.generateBillPdf(bill, customer);
		} catch (IOException e) {
			throw new BadRequestException("Unable to process your request.");
		}
		return contents;
	}

	/**
	 * Returns all the bills for a provided custId
	 * 
	 * @param custId
	 * @return List of Bill
	 */
	public List<Bill> getAllBills(Long custId) {
		return billDao.findAllByCustomerid(custId);
	}

	/**
	 * Returns all the payments for a provided custId
	 * 
	 * @param custId
	 * @return List of Payment
	 */
	public List<Payment> getAllPayments(Long custId) {
		return paymentRepository.findAllByCustId(custId);
	}

	/**
	 * Presist the feedback
	 * 
	 * @param currentUser
	 * @param feed
	 * @return Feedback
	 */
	public Feedback giveFeedback(UserPrincipal currentUser, Feedback feed) {
		feed.setCustId(currentUser.getId());
		return feedbackDao.save(feed);
	}

	/**
	 * Sends password reset link with token to provided email
	 * @param email
	 * @param token
	 */
	private void sendPasswordResetToken(String email, String token) {
		String appUrl = appProperties.getFrontEndUrl();
		String confirmationPath = "/reset-password";
		String parameterName = "token";
		String mailText = "Hi !\nTo reset your password, please click here : " + appUrl + confirmationPath + "?"
				+ parameterName + "=" + token;
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(email);
		mailMessage.setSubject("Password Reset!");
		mailMessage.setText(mailText);
		emailSenderService.sendASynchronousMail(mailMessage);
	}

	/**
	 * Send verification mail to `email` with `token`.
	 * @param email
	 * @param token
	 * @param name  
	 */
	private void sendVerificationMail(String email, String token, String name) {
		String appUrl = appProperties.getApplicationUrl();
		String confirmationPath = "/auth/verify-email";
		String parameterName = "token";
		String mailText = "Hi " + name + " !\nTo confirm your account, please click here : " + appUrl + confirmationPath
				+ "?" + parameterName + "=" + token;
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(email);
		mailMessage.setSubject("Verify Email!");
		mailMessage.setText(mailText);
		emailSenderService.sendASynchronousMail(mailMessage);
	}

}
