package com.ebs.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ebs.auth.repository.ApplicationUserDao;

@Service
public class ApplicationUserService implements UserDetailsService {

	private final ApplicationUserDao applicationUserDao;

	@Autowired
	public ApplicationUserService(ApplicationUserDao applicationUserDao) {
		super();
		this.applicationUserDao = applicationUserDao;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return applicationUserDao.selectApplicationUserByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s not found", username)));
	}

}
