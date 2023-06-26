package com.kafka101.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.kafka101.producer.car.Car;

import jakarta.annotation.PostConstruct;

@Component
public class InitProducer {

	private final Logger LOG = LoggerFactory.getLogger(getClass());

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	/**
	 * Runs automaticaly as soon Spring starts
	 * 
	 * @throws InterruptedException
	 */
	@PostConstruct
	public void init() throws InterruptedException {
		Car car1 = new Car("CAR_1");
		Car car2 = new Car("CAR_2");

		String adasData = null;
		String lidarData = null;
		while (true) {

			// CAR 1
			adasData = car1.getAdasData().toString();
			LOG.info("Car 1 ADAS: " + adasData);
			kafkaTemplate.send("ADAS", adasData);

			lidarData = car1.getLidarData().toString();
			LOG.info("Car 1 LIDAR: " + lidarData);
			kafkaTemplate.send("LIDAR", lidarData);

			// CAR 2
			adasData = car2.getAdasData().toString();
			LOG.info("Car 2 ADAS: " + adasData);
			kafkaTemplate.send("ADAS", adasData);

			lidarData = car2.getLidarData().toString();
			LOG.info("Car 2 LIDAR: " + lidarData);
			kafkaTemplate.send("LIDAR", lidarData);

			Thread.sleep(1000);
		}
	}

}
