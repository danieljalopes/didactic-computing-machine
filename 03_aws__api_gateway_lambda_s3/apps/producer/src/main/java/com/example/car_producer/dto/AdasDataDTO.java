package com.example.car_producer.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;


/**
 * Represents ADAS Data 
 */
@Builder
@Data
@ToString
public class AdasDataDTO implements DTO{
	
	@Builder.Default
	private final String dataType = "ADAS";
	private String vin;
	private String geohash;
	private LocalDateTime dateTime;
	private Integer engineTemp;
	private Integer engineRotations;
	private Float speed;
}
