package scdf.salesgenerator.sale;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SaleDto {

	private String description;
	private Float amount;
	private String transactionId;
	private Timestamp transactionTimestamp;
	private Integer transactionType;
	
	
}



