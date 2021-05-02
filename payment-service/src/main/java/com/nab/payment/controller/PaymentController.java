package com.nab.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nab.payment.model.Payment;
import com.nab.payment.service.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {

	private PaymentService paymentSerivce;

	@Autowired
	public PaymentController(PaymentService paymentSerivce) {
		this.paymentSerivce = paymentSerivce;
	}

	@PostMapping
	public void paymentProcess(@RequestBody Payment payment) {
		paymentSerivce.paymentProcess(payment);
	}
}
