package com.capgemini.ebms.service;

import org.springframework.stereotype.Service;

import com.capgemini.ebms.dao.CustomerAuthenticationDao;

import org.springframework.beans.factory.annotation.Autowired;

@Service("customerAuthentication")
public class CustomerAuthenticationImpl implements CustomerAuthentication{

	@Autowired
	CustomerAuthenticationDao authenticationDao;
}
