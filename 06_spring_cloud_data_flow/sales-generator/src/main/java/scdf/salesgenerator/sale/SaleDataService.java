package scdf.salesgenerator.sale;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;

import java.util.concurrent.atomic.AtomicLong;
import java.util.function.BiFunction;
import java.util.random.RandomGenerator;

@Service
public class SaleDataService {
	private RandomGenerator generator = RandomGenerator.getDefault();
	public SaleDto sale ;

	public Flux<SaleDto> getSales(){
		BiFunction<AtomicLong, SynchronousSink<SaleDto>, AtomicLong> saleGenerator = (state , sink) -> {
			state.getAndSet(this.generator.nextLong());

			sink.next(sale);
			return state;
		};

		return Flux.generate(AtomicLong::new, saleGenerator);
	}

	/**
	 * Returns Current Sale
	 * @return
	 */
	public SaleDto getCurrentSale(){
		return this.sale;
	}
}
