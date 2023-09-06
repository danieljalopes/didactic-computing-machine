package scdf.salesgenerator;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import scdf.salesgenerator.sale.SaleGenerator;

@Component
public class InitProducer {
	
	@Autowired
	private SaleGenerator saleGenerator;
	
	/**
	 * Runs automatically as soon Spring starts
	 * This is triggered by <code>ApplicationReadyEvent</code>
	 * 
	 * @throws InterruptedException
	 */
	@EventListener(ApplicationReadyEvent.class)
	public void init() throws InterruptedException {
		
		Executor executor = Executors.newSingleThreadExecutor();
		
		executor.execute(saleGenerator);
	}

}
