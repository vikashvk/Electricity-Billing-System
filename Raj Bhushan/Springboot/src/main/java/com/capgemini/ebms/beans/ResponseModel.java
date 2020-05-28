package com.capgemini.ebms.beans;


import java.util.Date;
import java.util.List;

public class ResponseModel {
	private Date time;
	private boolean success;
	private Object result;
	private List<String> errors;

	public ResponseModel() {
		super();
	}

	public ResponseModel(boolean success, Object result) {
		super();
		this.time = new Date();
		this.success = success;
		this.result = result;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
}
