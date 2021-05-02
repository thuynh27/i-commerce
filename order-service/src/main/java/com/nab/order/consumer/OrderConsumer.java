package com.nab.order.consumer;

import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nab.order.dto.OrderPaymentDTO;
import com.nab.order.service.OrderService;

@Service
public class OrderConsumer {
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);

	private OrderService orderService;

	@Autowired
	public OrderConsumer(OrderService orderService) {
		this.orderService = orderService;
	}

	@RabbitListener(queues = "${queue.order.name}")
	public void receiveFromProductService(Message signalData) throws JsonMappingException, JsonProcessingException {
		LOGGER.info("================ Recived Data ============= ");
		String data = new String(signalData.getBody(), StandardCharsets.UTF_8);
		ObjectMapper objectMapper = new ObjectMapper();
		OrderPaymentDTO orderDTO = objectMapper.readValue(data, OrderPaymentDTO.class);
		orderService.updateOrderStatus(orderDTO);
		LOGGER.info("================ Process Data Successfully ============= ");
	}
}
