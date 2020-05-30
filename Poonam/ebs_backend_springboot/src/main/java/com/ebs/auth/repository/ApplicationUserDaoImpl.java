package com.ebs.auth.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.ebs.auth.model.ApplicationUser;
import com.ebs.auth.model.ApplicationUserRole;
import com.google.common.collect.Lists;

@Repository("applicationUserDao")
public class ApplicationUserDaoImpl implements ApplicationUserDao {
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public ApplicationUserDaoImpl(PasswordEncoder passwordEncoder) {
		super();
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
		return getApplicationUsers().stream().filter(applicationUser -> username.equals(applicationUser.getUsername()))
				.findFirst();
	}

	private List<ApplicationUser> getApplicationUsers() {
		List<ApplicationUser> applicationUsers = Lists.newArrayList(
				new ApplicationUser("annasmith", passwordEncoder.encode("password"),
						ApplicationUserRole.CUSTOMER.getGrantedAuthorities(), true, true, true, true),
				new ApplicationUser("annasmith@anna", passwordEncoder.encode("password"),
						ApplicationUserRole.CUSTOMER.getGrantedAuthorities(), true, true, true, true),
				new ApplicationUser("linda", passwordEncoder.encode("password123"),
						ApplicationUserRole.ADMIN.getGrantedAuthorities(), true, true, true, true),
				new ApplicationUser("tom", passwordEncoder.encode("password123"),
						ApplicationUserRole.ADMIN.getGrantedAuthorities(), true, true, true, true));
		return applicationUsers;
	}
}
