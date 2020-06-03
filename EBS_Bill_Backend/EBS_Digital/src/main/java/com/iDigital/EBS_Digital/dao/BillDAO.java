package com.iDigital.EBS_Digital.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iDigital.EBS_Digital.model.Bill;

public interface BillDAO extends JpaRepository<Bill, Integer>{

}
