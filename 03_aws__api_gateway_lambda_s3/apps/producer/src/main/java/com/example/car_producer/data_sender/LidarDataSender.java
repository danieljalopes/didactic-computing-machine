package com.example.car_producer.data_sender;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.car_producer.dto.DTO;
import com.example.car_producer.factory.DtoFactory;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Scope("prototype")
@Data
@Slf4j
@NoArgsConstructor
public class LidarDataSender implements Runnable {

	private String vin;
	private String url;

	@Autowired
	private RestTemplate restTempate;

	@Autowired
	private DtoFactory dtoFactory;

	public LidarDataSender(String vin, String url) {
		this.vin = vin;
		this.url = url;
	}

	@Override
	public void run() {
		while (true) {
			try {
				HttpHeaders headers = new HttpHeaders();
				headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
				headers.add("DataType", "LIDAR");
				HttpEntity<DTO> entity = new HttpEntity<DTO>(dtoFactory.generate("LIDAR", vin), headers);
				String response = restTempate.exchange(url, HttpMethod.POST, entity, String.class).getBody();

				log.info("Request: " + entity.getBody());
				log.info("Response: " + response);

				Thread.sleep(3000);
			} catch (InterruptedException e) {
				log.error("LidarDataSender", e);
			} catch (Exception e) {
				log.error("LidarDataSender", e);
			}
		}
	}

}
