/**
 * 
 */
package com.ebs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebs.payload.PaymentRequest;
import com.ebs.service.PaymentService;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;

/**
 * @author yequa
 *
 */

@RequestMapping("/payment")
@RestController
public class PaymentController {
	@Autowired
	private PaymentService paymentService;

	@PostMapping("/charge")
	public Charge chargeCard(@RequestBody PaymentRequest paymentRequest) throws StripeException {
		String token = paymentRequest.getToken();
		Long billId = paymentRequest.getBillId();
		return paymentService.chargeCard(token, billId);
	}
}
