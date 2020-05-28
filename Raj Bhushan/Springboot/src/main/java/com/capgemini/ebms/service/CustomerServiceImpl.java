package com.capgemini.ebms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.capgemini.ebms.beans.CustomerAuthentication;
import com.capgemini.ebms.beans.CustomerDetail;
import com.capgemini.ebms.beans.RegistrationModel;
import com.capgemini.ebms.dao.CustomerAuthenticationDao;
import com.capgemini.ebms.dao.CustomerDao;
import com.capgemini.ebms.exception.UserException;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerDao customerDao;

	@Autowired
	CustomerAuthenticationDao authenticationDao;

//For registering the user
	public CustomerDetail register(RegistrationModel registrationDetails) throws UserException {

		CustomerAuthentication auth = new CustomerAuthentication();
		auth.setCustPassword(registrationDetails.getCustPassword());
		System.out.println(auth);
		CustomerAuthentication registerdUser = authenticationDao.save(auth);
		System.out.println(registerdUser);
		CustomerDetail customerDetails = new CustomerDetail();
		customerDetails.setCustId(registerdUser.getCustId());
		customerDetails.setCustMobile(registrationDetails.getCustMobile());
		customerDetails.setCustName(registrationDetails.getCustName());
		CustomerDetail register = customerDao.save(customerDetails);

		if (register == null) {
			throw new UserException("User account could not be created.", HttpStatus.UNAUTHORIZED);
		}
		return register;
	}

//Code for user authentication
	public Long login(long userId, String userPassword) throws UserException {
		Long returnedUserId = authenticationDao.login(userId, userPassword);
		System.out.println(returnedUserId);
		if (returnedUserId == null) {
			throw new UserException("Incorrect Customer Id / Password.", HttpStatus.UNAUTHORIZED);
		}
		return returnedUserId;
	}

}
