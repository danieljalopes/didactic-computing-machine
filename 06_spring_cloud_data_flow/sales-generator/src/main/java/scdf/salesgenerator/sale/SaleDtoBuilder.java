package scdf.salesgenerator.sale;

import java.sql.Timestamp;
import java.util.random.RandomGenerator;

public class SaleDtoBuilder {
	
	private static RandomGenerator generator = RandomGenerator.getDefault();
	
	public static  SaleDto build() {
		
		
		return SaleDto.builder()
				.transactionId(generateTransactionId())
				.amount(generateAmount())
				.transactionTimestamp(generateTimestamp())
				.transactionType(generateTransactionType())
				.description(generateDescription())
				.build();
	}
	
	
	private static String generateTransactionId() {
		int leftLimit = 48; // numeral '0'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 10;
	    

	    return generator.ints(leftLimit, rightLimit + 1)
	      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
	      .limit(targetStringLength)
	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	      .toString();
	}
	
	
	private static Float generateAmount() {
		return generator.nextFloat(0F, 1000000F);
	}
	
	
	private static Timestamp generateTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}
	
	private static Integer generateTransactionType() {
		
		return generator.nextInt(1, 4);
	}
	
	private static String generateDescription() {
		String [] items = {"SHOES", "NECKLACE", "MACKUP KIT", "LIPSTICK"};
		
		return items[generator.nextInt(0, 4)];
	}

}
