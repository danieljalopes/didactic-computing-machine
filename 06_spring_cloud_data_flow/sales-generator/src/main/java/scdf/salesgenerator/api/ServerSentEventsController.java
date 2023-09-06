package scdf.salesgenerator.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import scdf.salesgenerator.sale.SaleDataService;
import scdf.salesgenerator.sale.SaleDto;

import java.time.Duration;
import java.util.stream.Stream;

@RestController
@RequestMapping("/server-sent-events")
public class ServerSentEventsController {

    @Autowired
    private SaleDataService saleDataService;

    @GetMapping(path = "/sales", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<SaleDto> sales(){

        Flux<Long> interval = Flux.interval(Duration.ofSeconds(1));
        Flux<SaleDto> events = Flux.fromStream(Stream.generate(() -> saleDataService.getCurrentSale())).log();
        return Flux.zip(events, interval, (key, value) -> key);
    }
}
