package com.capgemini.ebms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.capgemini.ebms.beans.CustomerAuthentication;

public interface CustomerAuthenticationDao extends JpaRepository<CustomerAuthentication, Long> {
	@Query("select c.custId from CustomerAuthentication c where custId=?1 and custPassword=?2")
	Long login(long custId, String custPassword);
}
