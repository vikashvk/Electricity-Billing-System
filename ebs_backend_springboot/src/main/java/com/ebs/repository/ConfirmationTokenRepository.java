package com.ebs.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ebs.model.ConfirmationToken;

public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, Long> {
    Optional<ConfirmationToken> findByConfirmationToken(String confirmationToken);
}