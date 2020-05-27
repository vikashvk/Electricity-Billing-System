package com.capgemini.ebms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.ebms.beans.CustomerDetail;
import com.capgemini.ebms.service.CustomerAuthentication;
import com.capgemini.ebms.service.CustomerService;





@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/EBMS")
public class CustomerController {
	@Autowired
	CustomerService customerService;
	
	@Autowired
	CustomerAuthentication customerAuthentication;

	// For registering the user , user may be librarian or student
	@PostMapping("/register")
	public CustomerDetail register(@RequestBody CustomerDetail customer) {
		return customerService.register(customer);
	}

	// For login authentication of the user
	@GetMapping("/login/{custId}/{custPassword}")
	public CustomerDetail Login(@PathVariable long custId, @PathVariable String custPassword) {
		return customerService.login(custId, custPassword);
	}
	
   
}
