package com.ebs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebs.model.Bill;
import com.ebs.model.CustomerDetail;
import com.ebs.repository.BillDAO;
import com.ebs.repository.CustomerDetailRespository;

@Service
public class BillService_Impl {
	@Autowired
	private BillDAO billdao;
	@Autowired
	private CustomerDetailRespository customerDetailRespository;

	CustomerDetail custd;
	Bill bill;

	public Bill saveBill(Bill bill) {
		bill.setBilldate("09-06-2019");
		bill.setDuedate("12-06-2019");
		return billdao.save(bill);
	}

	public List<Bill> getBills() {
		return billdao.findAll();
	}

	public Bill getBillById(Long id) {
		return billdao.findById(id).orElse(null);
	}

	public List<Bill> getAllBillByCustomerId(Long custId) {
		return billdao.findAllByCustomerid(custId);
	}

	public String deleteBill(Long id) {
		billdao.deleteById(id);
		return "Bill removed!!" + id;
	}

	public Bill updateBill(Long id, Bill bill) {
		Bill previousBill = billdao.findById(id).orElse(null);
		previousBill.setFlagpaid(bill.getFlagpaid());
		previousBill.setBillfine(bill.getBillfine());
		previousBill.setBilldate(bill.getBilldate());
		previousBill.setDuedate(bill.getDuedate());
		previousBill.setUnitconsumption(bill.getUnitconsumption());
		previousBill.setUnitrate(bill.getUnitrate());
		previousBill.setBillamount(bill.getBillamount());
		previousBill.setCity(bill.getCity());
		previousBill.setState(bill.getState());
		previousBill.setMobilenumber(bill.getMobilenumber());
		return billdao.save(previousBill);
	}

	// Methods to fetch customer details

	public CustomerDetail saveCustomerDetails(CustomerDetail customerDetail) {
		return customerDetailRespository.save(customerDetail);
	}

	public List<CustomerDetail> getCustomerDetails() {
		return customerDetailRespository.findAll();
	}

//	public Bill getBillDetails(Long billId, Long custId) {
//		CustomerDetail customerdetail= customerdao.findById(custId).orElseThrow(null);
//		Bill bill=new Bill();
//		bill.setCustomerid(customerdetail);
//		return bill;
//	}
//
//	public List<Bill> getAllBills(Long custId) {
//		CustomerDetail customer  =new CustomerDetail();
//		customer.setId(custId);
//		return billdao.findAllByCustomer(customer);
//	}

}
