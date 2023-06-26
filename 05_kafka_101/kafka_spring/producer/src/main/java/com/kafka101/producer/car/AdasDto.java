package com.kafka101.producer.car;

import java.time.LocalDateTime;
import java.util.Random;

public record AdasDto(String vin, String geohash, LocalDateTime dateTime, Integer engineTemp,Integer engineRotations,Float speed) implements Dto{

	
	public static AdasDto generate(String vin, Random random, String geoshash ) {
		
		
		return new AdasDto(vin, geoshash, LocalDateTime.now(), random.nextInt(), random.nextInt(),
				random.nextFloat());
	}
	
	
}
