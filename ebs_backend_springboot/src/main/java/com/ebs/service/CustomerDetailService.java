package com.ebs.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebs.exception.ResourceNotFoundException;
import com.ebs.model.Address;
import com.ebs.model.CustomerDetail;
import com.ebs.repository.CustomerDetailRespository;

@Service
public class CustomerDetailService {
	@Autowired
	private CustomerDetailRespository customerDetailRespository;

	// takes user id and return Customer Detail
	public CustomerDetail getCurrentUserDetails(Long userId) {
		return customerDetailRespository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
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
}
