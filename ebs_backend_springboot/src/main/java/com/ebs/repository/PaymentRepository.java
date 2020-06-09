package com.ebs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ebs.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
	List<Payment> findAllByCustId(Long custId);
}
