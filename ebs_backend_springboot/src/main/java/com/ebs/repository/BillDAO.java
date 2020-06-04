package com.ebs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ebs.model.Bill;
import com.ebs.model.CustomerDetail;

public interface BillDAO extends JpaRepository<Bill, Long>{

	List<Bill> findAllByCustomer(CustomerDetail customer);

}