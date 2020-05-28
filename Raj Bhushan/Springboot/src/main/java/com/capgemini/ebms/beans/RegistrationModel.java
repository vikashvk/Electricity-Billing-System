package com.capgemini.ebms.beans;

public class RegistrationModel {
	private long custId;
	private String custName;
	private String custMobile;
	private String custPassword;
	public String getCustPassword() {
		return custPassword;
	}

	public void setCustPassword(String custPassword) {
		this.custPassword = custPassword;
	}

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

	@Override
	public String toString() {
		return "CustomerDetail [custId=" + custId + ", custName=" + custName + ", custMobile=" + custMobile + "]";
	}

}
