package com.ebs.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Bill_Details")
public class Bill {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ebs_bill_seq_gen")
	@SequenceGenerator(name = "ebs_bill_seq_gen", initialValue = 12500, sequenceName = "ebs_bill_seq")
	private Long id;
	private String city;
	private String state;
	private Long mobilenumber;
	private int flagpaid;
	private int billamount;
	private int billfine;
	private String billdate;
	private String duedate;
	private int unitconsumption;
	private int unitrate;
	private Long customerid;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Long getMobilenumber() {
		return mobilenumber;
	}
	public void setMobilenumber(Long mobilenumber) {
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
	public Long getCustomerid() {
		return customerid;
	}
	public void setCustomerid(Long customerid) {
		this.customerid = customerid;
	}
	@Override
	public String toString() {
		return "Bill [id=" + id + ", city=" + city + ", state=" + state + ", mobilenumber=" + mobilenumber
				+ ", flagpaid=" + flagpaid + ", billamount=" + billamount + ", billfine=" + billfine + ", billdate="
				+ billdate + ", duedate=" + duedate + ", unitconsumption=" + unitconsumption + ", unitrate=" + unitrate
				+ "]";
	}
	
	
	

}
