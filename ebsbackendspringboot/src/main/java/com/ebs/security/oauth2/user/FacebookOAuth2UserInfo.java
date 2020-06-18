package com.ebs.security.oauth2.user;

import java.util.Map;

/**
 * UserInfo class for Google
 * 
 * @author Poonamchand Sahu
 *
 */
public class FacebookOAuth2UserInfo extends OAuth2UserInfo {
	public FacebookOAuth2UserInfo(Map<String, Object> attributes) {
		super(attributes);
	}

	@Override
	public String getId() {
		return (String) attributes.get("id");
	}

	@Override
	public String getFirstName() {
		return (String) attributes.get("first_name");
	}

	@Override
	public String getLastName() {
		return (String) attributes.get("last_name");
	}

	@Override
	public String getFullName() {
		return String.format("%s %s", getFirstName(), getLastName());
	}

	@Override
	public String getEmail() {
		return (String) attributes.get("email");
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getImageUrl() {
		if (attributes.containsKey("picture")) {
			Map<String, Object> pictureObj = (Map<String, Object>) attributes.get("picture");
			if (pictureObj.containsKey("data")) {
				Map<String, Object> dataObj = (Map<String, Object>) pictureObj.get("data");
				if (dataObj.containsKey("url")) {
					return (String) dataObj.get("url");
				}
			}
		}
		return null;
	}
}
