package com.nab.payment.service;

import org.springframework.stereotype.Service;

import com.nab.payment.model.Payment;

@Service
public interface PaymentService {

	public void paymentProcess(Payment payment);
}
