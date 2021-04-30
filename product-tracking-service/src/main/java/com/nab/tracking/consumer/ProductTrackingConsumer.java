package com.nab.tracking.consumer;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ProductTrackingConsumer {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductTrackingConsumer.class);

    @RabbitListener(queues = "${queue.tracking.name}")
    public void receiveFromProductService(String signalData) throws IOException {
        LOGGER.info("================ Recived Data ============= ");
        LOGGER.info("================ signalData ============= ");

    }

}
