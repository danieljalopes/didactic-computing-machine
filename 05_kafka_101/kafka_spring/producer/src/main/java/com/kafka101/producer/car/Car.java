package com.kafka101.producer.car;

import java.util.Objects;
import java.util.Random;

public class Car {

	private Random random = new Random();

	private String vin;

	public Car(String vin) {
		this.vin = vin;
	}

	public AdasDto getAdasData() {
		return AdasDto.generate(vin, random, generateGeohash());
	}

	public LidarDto getLidarData() {
		return LidarDto.generate(vin, random, generateGeohash());
	}

	private static String generateGeohash() {
		int leftLimit = 48; // numeral '0'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 9;
		Random random = new Random();

		String geohash = random.ints(leftLimit, rightLimit + 1)
				.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

		return geohash;
	}

	@Override
	public int hashCode() {
		return Objects.hash(vin);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		return Objects.equals(vin, other.vin);
	}

	@Override
	public String toString() {
		return "Car [random=" + random + ", vin=" + vin + "]";
	}
	
	

}
