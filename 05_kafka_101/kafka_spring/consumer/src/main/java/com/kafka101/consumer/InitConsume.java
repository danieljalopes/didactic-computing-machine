package com.kafka101.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class InitConsume {

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	@KafkaListener(topics = "LIDAR")
	public void consumeLidar(String message) {
		LOG.info("TOPIC: LIDAR -- message: " + message);
	}
	
	@KafkaListener(topics = "ADAS")
	public void consumeADAS(String message) {
		LOG.info("TOPIC: ADAS -- message: " + message);
	}
}
