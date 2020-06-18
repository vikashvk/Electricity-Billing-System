package com.ebs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.ebs.config.AppProperties;
/**
 * @author Poonamchand Sahu
 * 
 * */
@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class EbsApplication extends SpringBootServletInitializer{
	public static void main(String[] args) {
		SpringApplication.run(EbsApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(EbsApplication.class);
	}
	
}
