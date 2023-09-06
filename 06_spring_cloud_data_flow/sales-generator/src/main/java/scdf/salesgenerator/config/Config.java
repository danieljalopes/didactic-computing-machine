package scdf.salesgenerator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import scdf.salesgenerator.sale.SaleDataService;


/**
 * Configure Kafka settings
 * 
 */
@Configuration
public class Config {
	
	@Bean
	@Scope("singleton")
	SaleDataService saleData() {
		return new SaleDataService();
	}

}
