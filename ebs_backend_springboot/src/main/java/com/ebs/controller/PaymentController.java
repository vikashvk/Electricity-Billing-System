/**
 * 
 */
package com.ebs.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebs.service.StripeClient;
import com.stripe.model.Charge;

/**
 * @author yequa
 *
 */

@RequestMapping("/payment")
@RestController
public class PaymentController {
 private StripeClient stripeClient;
 
 @Autowired
 PaymentController(StripeClient stripeClient) {
     this.stripeClient = stripeClient;
 }

 @PostMapping("/charge")
 public Charge chargeCard(javax.servlet.http.HttpServletRequest request) throws Exception {
     String token = request.getHeader("token");
     Double amount = Double.parseDouble(request.getHeader("amount"));
     return this.stripeClient.chargeNewCard(token, amount);
 }
}
