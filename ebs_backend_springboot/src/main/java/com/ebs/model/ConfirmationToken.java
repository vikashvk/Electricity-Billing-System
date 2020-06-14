package com.ebs.model;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class ConfirmationToken {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "token_id")
	private Long tokenid;

	@Column(name = "confirmation_token", nullable = false)
	private String confirmationToken;

	@Temporal(TemporalType.TIMESTAMP)
	private Date expirationTime;

	private Long custId;

	public ConfirmationToken() {
		super();
	}

	public ConfirmationToken(Long custId) {
		this.expirationTime = generateExpirationTime();
		this.custId = custId;
		confirmationToken = UUID.randomUUID().toString();
	}

	public Long getTokenid() {
		return tokenid;
	}

	public void setTokenid(Long tokenid) {
		this.tokenid = tokenid;
	}

	public String getConfirmationToken() {
		return confirmationToken;
	}

	public void setConfirmationToken(String confirmationToken) {
		this.confirmationToken = confirmationToken;
	}

	public Date getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(Date expirationTime) {
		this.expirationTime = expirationTime;
	}

	public Long getCustId() {
		return custId;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
	}

	private static Date generateExpirationTime() {
		Date now = new Date();
		// Adding 5 days to current Date Time
		// Token will be expired after 5 days
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(now);
		cal.add(Calendar.DATE, 5);
		return cal.getTime();
	}
}