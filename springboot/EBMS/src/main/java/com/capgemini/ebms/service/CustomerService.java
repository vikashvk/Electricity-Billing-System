package com.capgemini.ebms.service;

import java.util.List;

import com.capgemini.ebms.beans.CustomerAuthentication;
import com.capgemini.ebms.beans.CustomerDetail;

public interface CustomerService {
//For registering the user
	CustomerDetail register(CustomerDetail customer);

	CustomerDetail login(long custId, String custPassword);
	
}
