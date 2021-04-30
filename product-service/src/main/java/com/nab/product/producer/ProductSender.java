package com.nab.product.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

@Service
@EnableAsync
public class ProductSender {

	private final RabbitTemplate rabbitTemplate;

    @Value("${queue.tracking.name}")
    private   String queueName;

    @Value("${exchange.product-exchange.name}")
    private String exchangeName;

    @Autowired
    public ProductSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * {@code Product Sender} : send to Product Tracking Service
     *
     *  exchangeName : exchangeName
     *  route key : exchangeName
     *  message : message
     */
    @Async
    public void sendMessageToQueue(String jsonMessage) {
        rabbitTemplate.convertAndSend(exchangeName, exchangeName, jsonMessage);
    }
}
