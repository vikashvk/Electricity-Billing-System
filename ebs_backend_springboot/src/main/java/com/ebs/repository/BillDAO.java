package com.ebs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ebs.model.Bill;

@Repository
public interface BillDAO extends JpaRepository<Bill, Long> {
	List<Bill> findAllByCustomerid(Long custId);
}
