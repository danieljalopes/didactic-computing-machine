package com.example.car_producer.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import com.example.car_producer.data_sender.AdasDataSender;
import com.example.car_producer.data_sender.LidarDataSender;

@Component
public class ApplicationStart {
	
	String resourceUrl = "https://xxxxxxx.execute-api.eu-west-1.amazonaws.com/production";
	
	String lidarEndpoint = "lidar";
	String fullUrlLidar = resourceUrl + "/" + lidarEndpoint;

	String adasEndPoint = "adas";
	String fullUrlAdas = resourceUrl + "/" + adasEndPoint;
	String vin = "xxxxxxxxx";
	

	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private TaskExecutor taskExecutor;
	
	@PostConstruct
	public void startLidar() {
		
		LidarDataSender lidarSender = applicationContext.getBean(LidarDataSender.class);
		lidarSender.setVin(vin);
		lidarSender.setUrl(fullUrlLidar);
		taskExecutor.execute(lidarSender);
		 
		
		AdasDataSender adasDataSender = applicationContext.getBean(AdasDataSender.class);
		adasDataSender.setVin(vin);
		adasDataSender.setUrl(fullUrlLidar);
		taskExecutor.execute(adasDataSender);
		
	}
}
