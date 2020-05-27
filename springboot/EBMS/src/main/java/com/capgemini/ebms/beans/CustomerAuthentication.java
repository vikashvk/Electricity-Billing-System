package com.capgemini.ebms.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EBMS_cust_authentication")
public class CustomerAuthentication {
    @Id
	private long custId;
	
	@Column(length=30)
	private String custPassword;

	public long getCustId() {
		return custId;
	}

	public void setCustId(long custId) {
		this.custId = custId;
	}

	public String getCustPassword() {
		return custPassword;
	}

	public void setCustPassword(String custPassword) {
		this.custPassword = custPassword;
	}

	public CustomerAuthentication(long custId, String custPassword) {
		super();
		this.custId = custId;
		this.custPassword = custPassword;
	}

	public CustomerAuthentication() {
		super();
	}

	@Override
	public String toString() {
		return "CustomerAuthentication [custId=" + custId + ", custPassword=" + custPassword + "]";
	}

	

	
	
	
}
