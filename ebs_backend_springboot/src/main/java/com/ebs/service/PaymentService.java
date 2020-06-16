package com.ebs.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebs.exception.ResourceNotFoundException;
import com.ebs.model.Bill;
import com.ebs.model.Payment;
import com.ebs.repository.BillDAO;
import com.ebs.repository.PaymentRepository;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;

@Service
public class PaymentService {
	@Autowired
	private BillDAO billDao;
	@Autowired
	private StripeClient stripeClient;
	@Autowired
	private PaymentRepository paymentRepository;

	public Charge chargeCard(String token, Long billId) throws StripeException, ResourceNotFoundException {
		Bill bill = billDao.findById(billId).orElseThrow(() -> new ResourceNotFoundException("Bill", "id", billId));
		int totalPayble = bill.getBillamount() + bill.getBillfine();
		Charge charge = this.stripeClient.chargeNewCard(token, totalPayble);
		bill.setFlagpaid(1);
		billDao.save(bill);
		Payment payment = new Payment(totalPayble, true, todayDateInString(), bill.getCustomerid(),
				bill.getCustomerid());
		paymentRepository.save(payment);
		return charge;
	}

	private String todayDateInString() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-uuuu");
		LocalDateTime now = LocalDateTime.now();
		String nowString = dtf.format(now);
		return nowString;
	}
}
