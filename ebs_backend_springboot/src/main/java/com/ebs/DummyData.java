package com.ebs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.ebs.model.AuthProvider;
import com.ebs.model.User;
import com.ebs.repository.UserRepository;

@Component
public class DummyData {
	private UserRepository userRepository;

	private PasswordEncoder passwordEncoder;

	@Autowired
	public DummyData(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		super();
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.addDummyData();
	}

	public void addDummyData() {
		User user = new User();
		user.setEmail("annasmith@anna.anna");
		user.setEmailVerified(false);
		user.setImageUrl("https://cdn3.iconfinder.com/data/icons/avatars-flat/33/woman_9-512.png");
		user.setName("Anna Smith");
		user.setPassword(passwordEncoder.encode("password123"));
		user.setProvider(AuthProvider.local);
		userRepository.save(user);
	}
}
