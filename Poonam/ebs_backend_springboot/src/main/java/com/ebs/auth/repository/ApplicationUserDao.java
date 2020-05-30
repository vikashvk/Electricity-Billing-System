package com.ebs.auth.repository;

import java.util.Optional;

import com.ebs.auth.model.ApplicationUser;

public interface ApplicationUserDao {
	Optional<ApplicationUser> selectApplicationUserByUsername(String username);
}
