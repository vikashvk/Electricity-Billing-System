package com.ebs.payload;

import javax.validation.constraints.NotBlank;

public class ChangeCustomerDetailRequest {
	@NotBlank(message = "First name cannot be blank")
	private String firstName;
	@NotBlank(message = "Last name cannot be blank")
	private String lastName;
//	@Pattern(regexp = "^[6-9]\\d{9}$")
	@NotBlank(message = "Mobile no. cannot be blank")
	private String mobile;
	@NotBlank(message = "Line 1 cannot be blank")
	private String line1;
	private String line2 = "";
	@NotBlank(message = "City cannot be blank")
	private String city;
	@NotBlank(message = "State cannot be blank")
	private String state;
	@NotBlank(message = "Country cannot be blank")
	private String country;
//@Pattern(regexp = "^[1-9]\\d{5}$")
	@NotBlank(message = "Pincode cannot be blank")
	private String pincode;

	public ChangeCustomerDetailRequest() {
		super();
	}

	public ChangeCustomerDetailRequest(String firstName, String lastName, String mobile, String line1, String line2,
			String city, String state, String country, String pincode) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobile = mobile;
		this.line1 = line1;
		this.line2 = line2;
		this.city = city;
		this.state = state;
		this.country = country;
		this.pincode = pincode;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getLine1() {
		return line1;
	}

	public void setLine1(String line1) {
		this.line1 = line1;
	}

	public String getLine2() {
		return line2;
	}

	public void setLine2(String line2) {
		this.line2 = line2;
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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

}
