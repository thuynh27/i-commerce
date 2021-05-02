package com.nab.payment.service.impl;

import org.springframework.amqp.AmqpException;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nab.payment.dto.OrderPaymentDTO;
import com.nab.payment.model.Payment;
import com.nab.payment.repository.PaymentRepository;
import com.nab.payment.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

	private PaymentSender paymentSender;

	private PaymentRepository paymentRepository;

	public PaymentServiceImpl(PaymentSender paymentSender, PaymentRepository paymentRepository) {
		this.paymentSender = paymentSender;
		this.paymentRepository = paymentRepository;
	}

	@Override
	public void paymentProcess(Payment payment) {
		try {
			OrderPaymentDTO order = new OrderPaymentDTO();
			order.setOrderId(payment.getOrderId());
			order.setOrderStatus(payment.getOrderStatus());
			paymentSender.sendMessageToQueue(order);
			paymentRepository.save(payment);
		} catch (AmqpException | JsonProcessingException e) {
			e.printStackTrace();
		}
	}

}
