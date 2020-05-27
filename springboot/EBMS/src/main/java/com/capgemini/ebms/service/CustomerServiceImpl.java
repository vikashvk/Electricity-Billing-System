package com.capgemini.ebms.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.ebms.beans.CustomerDetail;
import com.capgemini.ebms.dao.CustomerAuthenticationDao;
import com.capgemini.ebms.dao.CustomerDao;
import com.capgemini.ebms.exception.UserCreationException;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerDao customerDao;
	
	@Autowired
	CustomerAuthenticationDao authenticationDao;
	
//For registering the user
	public CustomerDetail register(CustomerDetail user) {
		CustomerDetail register = customerDao.save(user);
//		       long custId = register.getCustId();
//		       String custPassword = register.getCustPassword();
		     
		
		if (register == null) {
			throw new UserCreationException("User Not Created!!");
		}
		return register;
	}

//Code for user authentication
	public CustomerDetail login(long userId, String userPassword) {
		CustomerDetail user1 = customerDao.login(userId, userPassword);
		return user1;
	}

	
		      
}
