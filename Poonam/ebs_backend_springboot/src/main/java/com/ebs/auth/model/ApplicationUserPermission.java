package com.ebs.auth.model;

public enum ApplicationUserPermission {
	CUSTOMER_READ("customer:read"), CUSTOMER_WRITE("customer:write");
	private final String permission;

	private ApplicationUserPermission(String permission) {
		this.permission = permission;
	}

	public String getPermission() {
		return permission;
	}
}
