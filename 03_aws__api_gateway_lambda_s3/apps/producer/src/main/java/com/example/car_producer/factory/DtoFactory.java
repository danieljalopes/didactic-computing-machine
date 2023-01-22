package com.example.car_producer.factory;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.example.car_producer.dto.AdasDataDTO;
import com.example.car_producer.dto.DTO;
import com.example.car_producer.dto.LidarDataDTO;

@Service
public class DtoFactory {

	private Random random = new Random();

	public DTO generate(String dto, String vin) {
		DTO result = null;
		switch (dto) {
		case "ADAS": {
			result = generateAdas(vin);
			break;
		}
		
		case "LIDAR": {
			result = generateLidar(vin);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + dto);
		}

		return result;
	}

	private AdasDataDTO generateAdas(String vin) {
		
	    
		return AdasDataDTO.builder().vin(vin)
				.geohash(generateGeohash())
				.dateTime(LocalDateTime.now()).engineTemp(random.nextInt())
				.engineRotations(random.nextInt()).speed(random.nextFloat()).build();
	}
	
	private LidarDataDTO generateLidar(String vin ) {
		return LidarDataDTO.builder()
				.vin(vin)
				.geohash(generateGeohash())
				.dateTime(LocalDateTime.now())
				.x(random.nextFloat())
				.y(random.nextFloat())
				.z(random.nextFloat())
				.lightIntensity(random.nextFloat())
				.build();
	}
	
	
	private String generateGeohash() {
		int leftLimit = 48; // numeral '0'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 9;
	    Random random = new Random();

	    String geohash = random.ints(leftLimit, rightLimit + 1)
	      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
	      .limit(targetStringLength)
	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	      .toString();
	    
	    return geohash;
	}

}
