package com.ebs;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.ebs.model.Address;
import com.ebs.model.AuthProvider;
import com.ebs.model.Bill;
import com.ebs.model.CustomerDetail;
import com.ebs.model.Payment;
import com.ebs.model.User;
import com.ebs.repository.AddressRepository;
import com.ebs.repository.BillDAO;
import com.ebs.repository.CustomerDetailRespository;
import com.ebs.repository.PaymentRepository;
import com.ebs.repository.UserRepository;

@Component
public class DummyData {
	private PasswordEncoder passwordEncoder;
	private UserRepository userRepository;
	private CustomerDetailRespository customerDetailRespository;
	private BillDAO billDao;
	private AddressRepository addressRepository;
	private PaymentRepository paymentRepository;

	@Autowired
	public DummyData(PasswordEncoder passwordEncoder, UserRepository userRepository,
			CustomerDetailRespository customerDetailRespository, BillDAO billDao, AddressRepository addressRepository,
			PaymentRepository paymentRepository) {
		super();
		this.passwordEncoder = passwordEncoder;
		this.userRepository = userRepository;
		this.customerDetailRespository = customerDetailRespository;
		this.billDao = billDao;
		this.addressRepository = addressRepository;
		this.paymentRepository = paymentRepository;
		addDummyData();
	}

	public void addDummyData() {
		Date now = new Date();
		User user = new User();
		user.setPassword(passwordEncoder.encode("password123"));
		user.setProvider(AuthProvider.local);
		user.setEmail("annasmith@anna.anna");
		user.setUpdatedAt(now);
		CustomerDetail customer = new CustomerDetail();
		Address address = new Address();
		address.setCity("Balod");
		address.setState("Chhattisgarh");
		address.setCountry("India");
		address.setLine1("Test Nagar");
		address.setPincode("784255");
		address.setUpdatedAt(now);
		address = addressRepository.save(address);
		customer.setImageUrl("https://cdn3.iconfinder.com/data/icons/avatars-flat/33/woman_9-512.png");
		customer.setUpdatedAt(now);
		customer.setUser(user);
		customer.setFirstName("Anna");
		customer.setLastName("Smith");
		customer.setMobile("9878787878");
		customer.setAddress(address);
		customer = customerDetailRespository.save(customer);
		Bill bill = new Bill();
		bill.setBillamount(1000);
		bill.setBilldate("08-05-2019");
		bill.setBillfine(520);
		bill.setCity("Balod");
		bill.setDuedate("12-05-2019");
		bill.setFlagpaid(1);
		bill.setMobilenumber("7878787878");
		bill.setState("Chhattisgarh");
		bill.setUnitconsumption(100);
		bill.setUnitrate(8);
		bill.setCustomer(customer);
		bill = billDao.save(bill);
		Payment payment = new Payment();
		payment.setAmount(bill.getBillamount() + bill.getBillfine());
		payment.setBillId(bill.getBillId());
		payment.setCustId(customer.getCustId());
		payment.setStatus(true);
		payment.setDate("11-05-2019");
		payment = paymentRepository.save(payment);
		System.out.println(payment);
		Bill bill1 = new Bill();
		bill1.setBillamount(4000);
		bill1.setBilldate("09-06-2019");
		bill1.setBillfine(520);
		bill1.setCity("Balod");
		bill1.setDuedate("15-06-2019");
		bill1.setFlagpaid(0);
		bill1.setMobilenumber("7878787878");
		bill1.setState("Chhattisgarh");
		bill1.setUnitconsumption(500);
		bill1.setUnitrate(8);
		bill1.setCustomer(customer);
		bill1 = billDao.save(bill1);
		System.out.println(bill1);

	}

}
