package com.capgemini.ebms.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.ebms.beans.CustomerAuthentication;

public interface CustomerAuthenticationDao extends JpaRepository<CustomerAuthentication,Long>{

}
