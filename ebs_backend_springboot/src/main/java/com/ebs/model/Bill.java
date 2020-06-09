package com.ebs.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity

@Table(name="EBS_BILL")
public class Bill {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ebs_bill_seq_gen")
	@SequenceGenerator(name = "ebs_bill_seq_gen", initialValue = 98700, sequenceName = "ebs_bill_seq")
	private Long billId;
	private String city;
	private String state;
	private String mobilenumber;
	private int flagpaid;
	private int billamount;
	private int billfine;
	private String billdate;
	private String duedate;
	private int unitconsumption;
	private int unitrate;
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private CustomerDetail customer;
	public Long getBillId() {
		return billId;
	}
	public void setBillId(Long billId) {
		this.billId = billId;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getMobilenumber() {
		return mobilenumber;
	}
	public void setMobilenumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
	}
	public int getFlagpaid() {
		return flagpaid;
	}
	public void setFlagpaid(int flagpaid) {
		this.flagpaid = flagpaid;
	}
	public int getBillamount() {
		return billamount;
	}
	public void setBillamount(int billamount) {
		this.billamount = billamount;
	}
	public int getBillfine() {
		return billfine;
	}
	public void setBillfine(int billfine) {
		this.billfine = billfine;
	}
	public String getBilldate() {
		return billdate;
	}
	public void setBilldate(String billdate) {
		this.billdate = billdate;
	}
	public String getDuedate() {
		return duedate;
	}
	public void setDuedate(String duedate) {
		this.duedate = duedate;
	}
	public int getUnitconsumption() {
		return unitconsumption;
	}
	public void setUnitconsumption(int unitconsumption) {
		this.unitconsumption = unitconsumption;
	}
	public int getUnitrate() {
		return unitrate;
	}
	public void setUnitrate(int unitrate) {
		this.unitrate = unitrate;
	}
	public CustomerDetail getCustomer() {
		return customer;
	}
	public void setCustomer(CustomerDetail customer) {
		this.customer = customer;
	}
	@Override
	public String toString() {
		return "Bill [billId=" + billId + ", city=" + city + ", state=" + state + ", mobilenumber=" + mobilenumber
				+ ", flagpaid=" + flagpaid + ", billamount=" + billamount + ", billfine=" + billfine + ", billdate="
				+ billdate + ", duedate=" + duedate + ", unitconsumption=" + unitconsumption + ", unitrate=" + unitrate
				+ ", customer=" + customer + "]";
	}

	
}