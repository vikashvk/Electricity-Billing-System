package com.ebs.payload;

/**
 * Used for payment processing
 * 
 * @author Vikash
 *
 */
public class PaymentRequest {
	private String token;
	private Long billId;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getBillId() {
		return billId;
	}

	public void setBillId(Long billId) {
		this.billId = billId;
	}
}
