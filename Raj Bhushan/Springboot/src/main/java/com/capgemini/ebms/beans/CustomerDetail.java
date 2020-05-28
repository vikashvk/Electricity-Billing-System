package com.capgemini.ebms.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EBMS_cust_detail")
public class CustomerDetail {

	@Id
	private long custId;

	@Column(length = 20)
	private String custName;

	@Column(length = 50)
	private String custMobile;

	public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCustMobile() {
		return custMobile;
	}

	public void setCustMobile(String custMobile) {
		this.custMobile = custMobile;
	}

	public CustomerDetail(long custId, String custName, String custMobile, String custPassword) {
		super();
		this.custId = custId;
		this.custName = custName;
		this.custMobile = custMobile;
	}

	public CustomerDetail() {
		super();
	}

	@Override
	public String toString() {
		return "CustomerDetail [custId=" + custId + ", custName=" + custName + ", custMobile=" + custMobile + "]";
	}

}
