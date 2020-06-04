package com.ebs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ebs.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
