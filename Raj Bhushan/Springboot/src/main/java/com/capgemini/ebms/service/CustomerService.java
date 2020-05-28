package com.capgemini.ebms.service;

import com.capgemini.ebms.beans.CustomerDetail;
import com.capgemini.ebms.beans.RegistrationModel;
import com.capgemini.ebms.exception.UserException;

public interface CustomerService {
//For registering the user
	CustomerDetail register(RegistrationModel registerDetails) throws UserException;

	Long login(long custId, String custPassword) throws UserException;

}
