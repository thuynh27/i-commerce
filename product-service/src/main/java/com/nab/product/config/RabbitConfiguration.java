package com.nab.product.config;

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

    @Value("${queue.tracking.name}")
    private String queueName;

    @Value("${exchange.product-exchange.name}")
    private String productExchange;
    
    @Bean
    Queue ordersQueue() {

        return QueueBuilder.durable(queueName).build();
    }
    
    @Bean
    Exchange ordersExchange() {
        return ExchangeBuilder.topicExchange(productExchange).build();
    }

    @Bean
    Binding binding(Queue ordersQueue, TopicExchange ordersExchange) {
        return BindingBuilder.bind(ordersQueue).to(ordersExchange).with(productExchange);
    }

}
