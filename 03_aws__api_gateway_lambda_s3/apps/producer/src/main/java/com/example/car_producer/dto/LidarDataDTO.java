package com.example.car_producer.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * Represents LIDAR Data
 * 
 *
 */
@Builder
@Data
@ToString
public class LidarDataDTO implements DTO{

	@Builder.Default
	private final String dataType = "LIDAR";
	private String vin;
	private String geohash;
	private LocalDateTime dateTime;
	private Float x;
	private Float y;
	private Float z;
	private Float lightIntensity;
}
