package com.ebs.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ebs.exception.BadRequestException;
import com.ebs.exception.ResourceNotFoundException;
import com.ebs.model.Address;
import com.ebs.model.AuthProvider;
import com.ebs.model.Bill;
import com.ebs.model.CustomerDetail;
import com.ebs.model.User;
import com.ebs.payload.ChangePasswordRequest;
import com.ebs.payload.SignUpRequest;
import com.ebs.repository.AddressRepository;
import com.ebs.repository.BillDAO;
import com.ebs.repository.CustomerDetailRespository;
import com.ebs.repository.UserRepository;
import com.ebs.security.UserPrincipal;
import com.ebs.util.PdfUtils;

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

	// takes user id and return Customer Detail
	public CustomerDetail getCurrentUserDetails(Long userId) {
		return customerDetailRespository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
	}

	public void registerUser(SignUpRequest signUpRequest) {
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			throw new BadRequestException("Email address already in use.");
		}
		Date now = new Date();
//Address 
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
		customerDetailRespository.save(customerDetail);
	}

	public CustomerDetail updateCustomerDetails(Long userId, CustomerDetail updatedCustomerDetail) {
		CustomerDetail customerDetail = customerDetailRespository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		Date now = new Date();
		Address address = customerDetail.getAddress();
		Address updatedAddress = updatedCustomerDetail.getAddress();
		address.setCity(updatedAddress.getCity());
		address.setCountry(updatedAddress.getCountry());
		address.setLine1(updatedAddress.getLine1());
		address.setLine2(updatedAddress.getLine2());
		address.setPincode(updatedAddress.getPincode());
		address.setUpdatedAt(now);
		customerDetail.setFirstName(updatedCustomerDetail.getFirstName());
		customerDetail.setLastName(updatedCustomerDetail.getLastName());
		customerDetail.setMobile(updatedCustomerDetail.getMobile());
		customerDetail.setAddress(address);
		customerDetail.setUpdatedAt(now);
		return customerDetailRespository.save(customerDetail);
	}

	public void changePassword(UserPrincipal currentUser, @Valid ChangePasswordRequest changePasswordRequest) {
		String requestCurrentPassword = changePasswordRequest.getCurrentPassword();
		String encodedCurrentPassword = currentUser.getPassword();
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

	public byte[] generateBillPdf(Long billId, Long custId) {
		Bill bill = getBillDetails(billId, custId);
		byte[] contents;
		try {
			contents = PdfUtils.generateBillPdf(bill);
		} catch (IOException e) {
			throw new BadRequestException("Unable to process your request.");
		}
		return contents;
	}

	public Bill getBillDetails(Long billId, Long custId) {
		Bill bill = billDao.findById(billId).orElseThrow(() -> new ResourceNotFoundException("Bill", "id", billId));
		if (bill.getCustomer().getId() != custId) {
			throw new BadRequestException("You are authorized to access this resource");
		}
		return bill;
	}

	public List<Bill> getAllBills(Long custId) {
		CustomerDetail customer  =new CustomerDetail();
		customer.setId(custId);
		return billDao.findAllByCustomer(customer);
	}
}