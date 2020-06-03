package com.iDigital.EBS_Digital.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iDigital.EBS_Digital.model.Bill;
import com.iDigital.EBS_Digital.service.BillService_Impl;

@RequestMapping("/api")
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
	public Bill findBillById(@PathVariable int id) {
		return service.getBillById(id);
	}
	
	@PutMapping("/updateBill")
	public Bill updateBill(@RequestBody Bill bill) {
		return service.updateBill(bill);
	}
	
	@DeleteMapping("/deleteBill/{id}")
	public String deleteBill(@PathVariable int id) {
		return service.deleteBill(id);
	}
	
	
	

}
