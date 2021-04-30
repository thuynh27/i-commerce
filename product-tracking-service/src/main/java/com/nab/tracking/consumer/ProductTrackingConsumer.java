package com.nab.tracking.consumer;

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
import com.nab.tracking.model.ProductTracking;
import com.nab.tracking.service.ProductTrackingService;

@Service
public class ProductTrackingConsumer {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductTrackingConsumer.class);

	private ProductTrackingService productTrackingService;

	@Autowired
	public ProductTrackingConsumer(ProductTrackingService productTrackingService) {
		this.productTrackingService = productTrackingService;
	}

	@RabbitListener(queues = "${queue.tracking.name}")
	public void receiveFromProductService(Message signalData) throws JsonMappingException, JsonProcessingException {
		LOGGER.info("================ Recived Data ============= ");
		String data = new String(signalData.getBody(), StandardCharsets.UTF_8);
		trackingProduct(data);
		LOGGER.info("================ Process Data Successfully ============= ");
	}

	private void trackingProduct(String data) throws JsonProcessingException, JsonMappingException {
		LOGGER.info("Result : {} ", data);
		ObjectMapper objectMapper = new ObjectMapper();
		ProductTracking productTracking = objectMapper.readValue(data, ProductTracking.class);
		productTrackingService.saveProductTracking(productTracking);
	}
}
