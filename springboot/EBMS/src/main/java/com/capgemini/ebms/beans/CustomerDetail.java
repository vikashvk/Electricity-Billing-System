package com.capgemini.ebms.beans;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


	@Entity
	@Table(name = "EBMS_cust_detail")
	public class CustomerDetail{

		@Id
		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ebs_cust_seq_gen")
		@SequenceGenerator(name = "ebs_cust_seq_gen", initialValue = 10000, sequenceName = "ebs_cust_seq") // to be created
		private long custId;
		
		@Column(length = 20)
		private String custName;
		
		@Column(length = 50)
		private String custMobile;
		
		@Column(length=30)
		private String custPassword;

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

		public String getCustPassword() {
			return custPassword;
		}

		public void setCustPassword(String custPassword) {
			this.custPassword = custPassword;
		}

		public CustomerDetail(long custId, String custName, String custMobile, String custPassword) {
			super();
			this.custId = custId;
			this.custName = custName;
			this.custMobile = custMobile;
			this.custPassword = custPassword;
		}

		public CustomerDetail() {
			super();
		}

		@Override
		public String toString() {
			return "CustomerDetail [custId=" + custId + ", custName=" + custName + ", custMobile=" + custMobile
					+ ", custPassword=" + custPassword + "]";
		}
		
		
		
		
		
		
	}



