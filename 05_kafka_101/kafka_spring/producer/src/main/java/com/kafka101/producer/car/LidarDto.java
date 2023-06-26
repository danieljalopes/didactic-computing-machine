package com.kafka101.producer.car;

import java.time.LocalDateTime;
import java.util.Random;

public record LidarDto(String vin, String geohash, LocalDateTime dateTime, Float x, Float y, Float z, Float lightIntensity)
		implements Dto {

	public static LidarDto generate(String vin, Random random, String geoshash) {
		return new LidarDto(vin, geoshash, LocalDateTime.now(), random.nextFloat(), random.nextFloat(), random.nextFloat(),
				random.nextFloat());
	}
}
