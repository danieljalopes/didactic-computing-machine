package scdf.salesgenerator.sale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value="saleGeneratorThread")
public class SaleGenerator implements Runnable{
	private Logger logger = LoggerFactory.getLogger(SaleGenerator.class);
	
	@Autowired
	private SaleDataService saleDataService;

	@Override
	public void run() {
		logger.info("----- Sale Generator Started -----");
		while (true) {
			saleDataService.sale = SaleDtoBuilder.build();
		}
	}

}
