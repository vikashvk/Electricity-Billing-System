package com.ebs.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ebs.model.CustomerDetail;
import com.ebs.model.User;
/**
 * @author Poonamchand Sahu
 * 
 * */
@Repository
public interface CustomerDetailRespository extends JpaRepository<CustomerDetail, Long> {
	Optional<CustomerDetail> findByUser(User user);

}
