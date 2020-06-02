package com.ebs.security.oauth2;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ebs.exception.OAuth2AuthenticationProcessingException;
import com.ebs.model.AuthProvider;
import com.ebs.model.CustomerDetail;
import com.ebs.model.User;
import com.ebs.repository.CustomerDetailRespository;
import com.ebs.repository.UserRepository;
import com.ebs.security.UserPrincipal;
import com.ebs.security.oauth2.user.OAuth2UserInfo;
import com.ebs.security.oauth2.user.OAuth2UserInfoFactory;

/**
 * @author Poonamchand Sahu
 * 
 */
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CustomerDetailRespository customerDetailRespository;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
		OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);

		try {
			return processOAuth2User(oAuth2UserRequest, oAuth2User);
		} catch (AuthenticationException ex) {
			throw ex;
		} catch (Exception ex) {
			// Throwing an instance of AuthenticationException will trigger the
			// OAuth2AuthenticationFailureHandler
			throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
		}
	}

	private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
		OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(
				oAuth2UserRequest.getClientRegistration().getRegistrationId(), oAuth2User.getAttributes());
		if (StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
			throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
		}

		Optional<User> userOptional = userRepository.findByEmail(oAuth2UserInfo.getEmail());
		User user;
		if (userOptional.isPresent()) {
			user = userOptional.get();
			if (!user.getProvider()
					.equals(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()))) {
				throw new OAuth2AuthenticationProcessingException(
						"Looks like you're signed up with " + user.getProvider() + " account. Please use your "
								+ user.getProvider() + " account to login.");
			}
			user = updateExistingUser(user, oAuth2UserInfo);
		} else {
			user = registerNewUser(oAuth2UserRequest, oAuth2UserInfo);
		}

		return UserPrincipal.create(user, oAuth2User.getAttributes());
	}

	private User registerNewUser(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo) {
		Date now = new Date();
		User user = new User();
		CustomerDetail customer = new CustomerDetail();
		user.setProvider(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()));
		user.setProviderId(oAuth2UserInfo.getId());
		user.setEmail(oAuth2UserInfo.getEmail());
		user.setUpdatedAt(now);
		customer.setFirstName(oAuth2UserInfo.getFirstName());
		customer.setLastName(oAuth2UserInfo.getLastName());
		customer.setImageUrl(oAuth2UserInfo.getImageUrl());
		customer.setUser(user);
		customer.setUpdatedAt(now);
		return customerDetailRespository.save(customer).getUser();
	}

	private User updateExistingUser(User existingUser, OAuth2UserInfo oAuth2UserInfo) {
		Date now = new Date();
		CustomerDetail existingCustomerDetail = customerDetailRespository.findByUser(existingUser).get();
		existingCustomerDetail.setFirstName(oAuth2UserInfo.getFirstName());
		existingCustomerDetail.setLastName(oAuth2UserInfo.getLastName());
		existingCustomerDetail.setImageUrl(oAuth2UserInfo.getImageUrl());
		existingCustomerDetail.setUpdatedAt(now);
		return customerDetailRespository.save(existingCustomerDetail).getUser();
	}

}
