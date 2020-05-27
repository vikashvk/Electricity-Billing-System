package com.capgemini.ebms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.capgemini.ebms.beans.CustomerDetail;

public interface CustomerDao extends JpaRepository<CustomerDetail,Long>{
	
   	@Query("from CustomerDetail where custId=?1 and custPassword=?2")
	 CustomerDetail login(long custId,String custPassword);

	
   
}
