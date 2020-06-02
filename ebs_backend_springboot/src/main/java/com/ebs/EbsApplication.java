package com.ebs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.ebs.config.AppProperties;
/**
 * @author Poonamchand Sahu
 * 
 * */
@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class EbsApplication {
	public static void main(String[] args) {
		SpringApplication.run(EbsApplication.class, args);
	}
	
}
