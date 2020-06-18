package com.ebs.security.oauth2.user;

import java.util.Map;

/**
 * Abstract class for getting the user detail from third party
 * 
 * @author Poonamchand Sahu
 *
 */
public abstract class OAuth2UserInfo {
	protected Map<String, Object> attributes;

	public OAuth2UserInfo(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public abstract String getId();

	public abstract String getFirstName();

	public abstract String getLastName();

	public abstract String getFullName();

	public abstract String getEmail();

	public abstract String getImageUrl();
}
