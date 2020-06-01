package com.capgemini.ebms.service;

import com.capgemini.ebms.beans.CustomerDetail;
import com.capgemini.ebms.beans.RegistrationModel;
import com.capgemini.ebms.exception.UserException;

public interface CustomerService {
    //For registering the customer
	CustomerDetail register(RegistrationModel registerDetails) throws UserException;
    
	//For login the customer
	Long login(long custId, String custPassword) throws UserException;
    
	//For viewing the profile of customer
	CustomerDetail viewCustomerProfile(long custId) throws UserException;
    
	//For editing the profile of customer
	CustomerDetail editProfile(long custId, CustomerDetail customer) throws UserException;
    
	//For changing the password
	boolean changePassword(long custId, String oldPassword, String newPassword) throws UserException;

}
