package com.nab.payment.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration  {

    @Value("${queue.order.name}")
    private String queueName;

    @Value("${exchange.payment-exchange.name}")
    private String paymentExchange;
    
    /* Handling Message error */
    @Value("${queue.dead-order.name}")
    private String deadQueue;
    
    @Bean
    Queue ordersQueue() {
        return QueueBuilder.durable(queueName)
                .withArgument("x-dead-letter-exchange", "")
                .withArgument("x-dead-letter-routing-key", deadQueue)
                .withArgument("x-message-ttl", 5000)
        		.build();
    }
    
    @Bean
    Queue deadLetterQueue() {
        return QueueBuilder.durable(deadQueue).build();
    }
    
    @Bean
    Exchange ordersExchange() {
        return ExchangeBuilder.topicExchange(paymentExchange).build();
    }

    @Bean
    Binding binding(Queue ordersQueue, TopicExchange ordersExchange) {
        return BindingBuilder.bind(ordersQueue).to(ordersExchange).with(paymentExchange);
    }

}
