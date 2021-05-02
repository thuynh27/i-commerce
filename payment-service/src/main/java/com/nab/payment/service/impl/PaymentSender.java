package com.nab.payment.service.impl;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nab.payment.dto.OrderPaymentDTO;

@Service
@EnableAsync
public class PaymentSender {

	private final RabbitTemplate rabbitTemplate;

    @Value("${queue.order.name}")
    private   String queueName;

    @Value("${exchange.payment-exchange.name}")
    private String exchangeName;

    @Autowired
    public PaymentSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * {@code Payment Sender} : send to OrderService
     *
     *  exchangeName : exchangeName
     *  route key : exchangeName
     *  message : message
     * @throws JsonProcessingException 
     * @throws AmqpException 
     */
    @Async
    public void sendMessageToQueue(OrderPaymentDTO order) throws AmqpException, JsonProcessingException {
        rabbitTemplate.convertAndSend(exchangeName, exchangeName,new ObjectMapper().writeValueAsString(order));
    }
}
