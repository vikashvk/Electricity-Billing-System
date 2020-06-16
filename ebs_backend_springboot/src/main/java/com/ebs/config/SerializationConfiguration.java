package com.ebs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Prevents error when serializing if bean is empty
 * @author Poonamchand Sahu
 */
@Configuration
public class SerializationConfiguration {
	@Bean
	public ObjectMapper objectMapper() {
        return new ObjectMapper().disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    }
}