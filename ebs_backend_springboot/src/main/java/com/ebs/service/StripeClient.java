/**
 * 
 */
package com.ebs.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;

/**
 * @author yequa
 *
 */
@Service
public class StripeClient {

	@Autowired
	StripeClient() {
		Stripe.apiKey = "sk_test_51GrqJ8Fp59dO3FUS5gCdvuipFYWcVa5Jcc6YVk63lclXL50XmvjLr2WyNXYH0ZH65168hh2RaIoUUOsQhVTIYwdv00mRFA9rH0";
	}

	public Customer createCustomer(String token, String email) throws StripeException {
		Map<String, Object> customerParams = new HashMap<String, Object>();
		customerParams.put("email", email);
		customerParams.put("source", token);
		return Customer.create(customerParams);
	}

	private Customer getCustomer(String id) throws StripeException {
		return Customer.retrieve(id);
	}

	public Charge chargeNewCard(String token, int amount) throws StripeException {
		Map<String, Object> chargeParams = new HashMap<String, Object>();
		chargeParams.put("amount", amount);
		chargeParams.put("currency", "inr");
		chargeParams.put("source", token);
		System.out.println(chargeParams);
		Charge charge = Charge.create(chargeParams);
		return charge;
	}

	public Charge chargeCustomerCard(String customerId, int amount) throws StripeException {
		String sourceCard = getCustomer(customerId).getDefaultSource();
		Map<String, Object> chargeParams = new HashMap<String, Object>();
		chargeParams.put("amount", amount);
		chargeParams.put("currency", "inr");
		chargeParams.put("customer", customerId);
		chargeParams.put("source", sourceCard);
		Charge charge = Charge.create(chargeParams);
		return charge;
	}

}
