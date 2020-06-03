package com.iDigital.EBS_Digital.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iDigital.EBS_Digital.dao.BillDAO;
import com.iDigital.EBS_Digital.model.Bill;

@Service
public class BillService_Impl {
	@Autowired
	private BillDAO billdao;
	
	public Bill saveBill(Bill bill) {
		return billdao.save(bill);
	}
	
	public List<Bill> getBills(){
		return billdao.findAll();
	}
	
	public Bill getBillById(int id) {
		return billdao.findById(id).orElse(null);
	}
	
	public String deleteBill(int id) {
		billdao.deleteById(id);
		return "Bill removed!!"+id;
	}
	
	public Bill updateBill(Bill bill) {
		Bill previousBill=billdao.findById(bill.getId()).orElse(null);
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
	
	

}
