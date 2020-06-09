package com.ebs.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Poonamchand Sahu
 * 
 */
@Entity
@Table(name = "EBS_USERS", uniqueConstraints = { @UniqueConstraint(columnNames = "email") })
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ebs_user_seq_gen")
	@SequenceGenerator(name = "ebs_user_seq_gen", initialValue = 10000, sequenceName = "ebs_user_seq")
	private Long custId;
	@Email
	@Column(nullable = false)
	private String email;
	@JsonIgnore
	 @Column(name = "emailVerified", nullable = false)
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean emailVerified = false;

	@JsonIgnore
	private String password;

	@NotNull
	@Enumerated(EnumType.STRING)
	@JsonIgnore
	private AuthProvider provider;
	@JsonIgnore
	private String providerId;
	@JsonIgnore
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;
//	@GeneratedValue(strategy = GenerationType.AUTO)
	

	

	public Long getCustId() {
		return custId;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
	}
   
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getEmailVerified() {
		return emailVerified;
	}

	public void setEmailVerified(Boolean emailVerified) {
		this.emailVerified = emailVerified;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public AuthProvider getProvider() {
		return provider;
	}

	public void setProvider(AuthProvider provider) {
		this.provider = provider;
	}

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
}
