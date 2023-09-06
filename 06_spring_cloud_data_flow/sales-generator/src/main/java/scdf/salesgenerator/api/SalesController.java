package scdf.salesgenerator.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import scdf.salesgenerator.sale.SaleDataService;
import scdf.salesgenerator.sale.SaleDto;

@RestController
@RequestMapping("/api/stream")
public class SalesController {
	
	private Logger logger = LoggerFactory.getLogger(SalesController.class);

	
	@Autowired
	private SaleDataService saleDataService;
	
	//https://projectreactor.io/docs/core/release/reference/

	@GetMapping(value="/sales", produces = MediaType.APPLICATION_NDJSON_VALUE)
	public Flux<SaleDto> getSales(){

		return saleDataService.getSales();
	}
}
