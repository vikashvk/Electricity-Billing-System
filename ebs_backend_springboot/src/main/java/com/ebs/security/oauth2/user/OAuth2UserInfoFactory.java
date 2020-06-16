package com.ebs.security.oauth2.user;

import com.ebs.exception.OAuth2AuthenticationProcessingException;
import com.ebs.model.AuthProvider;

import java.util.Map;

/**
 * Factory for getting Appropriate OAuth2UserInfo object for respective
 * AuthProvider
 * 
 * @author Poonamchand Sahu
 *
 */
public class OAuth2UserInfoFactory {

	public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
		if (registrationId.equalsIgnoreCase(AuthProvider.google.toString())) {
			return new GoogleOAuth2UserInfo(attributes);
		} else if (registrationId.equalsIgnoreCase(AuthProvider.facebook.toString())) {
			return new FacebookOAuth2UserInfo(attributes);
		} else {
			throw new OAuth2AuthenticationProcessingException(
					"Sorry! Login with " + registrationId + " is not supported yet.");
		}
	}
}