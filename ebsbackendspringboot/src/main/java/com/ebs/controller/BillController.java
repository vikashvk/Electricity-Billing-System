package com.ebs.controller;

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

import com.ebs.model.Bill;
import com.ebs.model.CustomerDetail;
import com.ebs.service.BillService_Impl;

@RequestMapping("/api/bill-module")
@CrossOrigin
@RestController
public class BillController {
	
	@Autowired
	private BillService_Impl service;
	
	@PostMapping("/addBill")
	public Bill addBill(@RequestBody Bill bill) {
		return service.saveBill(bill);
	}
	
	@GetMapping("/Bills")
	public List<Bill> findAllBills(){
		return service.getBills();
	}
	
	@GetMapping("/Bills/{id}")
	public Bill findBillById(@PathVariable Long id) {
		return service.getBillById(id);
	}
	@GetMapping("/Bills/all/{custId}")
	public List<Bill> getAllBillByCustomerId(@PathVariable Long custId) {
		return service.getAllBillByCustomerId(custId);
	}
	@PutMapping("/updateBill/{id}")
	public Bill updateBill(@PathVariable Long id,@RequestBody Bill bill) {
		return service.updateBill(id, bill);
	}
	
	@DeleteMapping("/deleteBill/{id}")
	public String deleteBill(@PathVariable Long id) {
		return service.deleteBill(id);
	}
	
	//Methods to fetch Customer Details
	
	@PostMapping("/addCustomer")
	public CustomerDetail addCustomerDetail(@RequestBody CustomerDetail customerdetail) {
		return service.saveCustomerDetails(customerdetail);
	}
	
	@GetMapping("/Customers")
	public List<CustomerDetail> findAllCustomers(){
		return service.getCustomerDetails();
	}
	
//	@GetMapping("/bills")
//	public ResponseEntity<?> getAllBills(@RequestBody Bill bill) {
//		List<Bill> bills = service.getAllBills(bill.getCustomerid());
//		return ResponseEntity.ok(bills);
//	}
	
	

	
	

}
