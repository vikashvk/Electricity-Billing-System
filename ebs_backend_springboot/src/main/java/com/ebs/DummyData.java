package com.ebs;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.ebs.model.AuthProvider;
import com.ebs.model.CustomerDetail;
import com.ebs.model.User;
import com.ebs.repository.CustomerDetailRespository;

@Component
public class DummyData {
	private PasswordEncoder passwordEncoder;
	private CustomerDetailRespository customerDetailRespository;

	
	@Autowired
	public DummyData(PasswordEncoder passwordEncoder, CustomerDetailRespository customerDetailRespository) {
		super();
		this.passwordEncoder = passwordEncoder;
		this.customerDetailRespository = customerDetailRespository;
		addDummyData();
	}



	public void addDummyData() {
		Date now = new Date();
		User user = new User();
		CustomerDetail customerDetail = new CustomerDetail();
		user.setEmail("annasmith@anna.anna");
		customerDetail.setImageUrl("https://cdn3.iconfinder.com/data/icons/avatars-flat/33/woman_9-512.png");
		customerDetail.setFirstName("Anna");
		customerDetail.setLastName("Smith");
		user.setPassword(passwordEncoder.encode("password123"));
		user.setProvider(AuthProvider.local);
		user.setUpdatedAt(now);
		customerDetail.setUpdatedAt(now);
		customerDetail.setUser(user);
		customerDetailRespository.save(customerDetail);
	}
}
