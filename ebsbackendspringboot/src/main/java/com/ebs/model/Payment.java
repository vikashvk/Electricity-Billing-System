package com.ebs.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "EBS_PAYMENTS")
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ebs_payment_seq_gen")
	@SequenceGenerator(name = "ebs_payment_seq_gen", initialValue = 98700, sequenceName = "ebs_payment_seq")
	private Long id;
	private Long custId;
	private double amount;
	private boolean status;
	private String date;
	private Long billId;
	
	public Payment() {
		super();
	}
	public Payment(double amount, boolean status, String date, Long custId, Long billId) {
		super();
		this.amount = amount;
		this.status = status;
		this.date = date;
		this.custId = custId;
		this.billId = billId;
	}
	public Long getBillId() {
		return billId;
	}
	public void setBillId(Long billId) {
		this.billId = billId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCustId() {
		return custId;
	}
	public void setCustId(Long custId) {
		this.custId = custId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
}
