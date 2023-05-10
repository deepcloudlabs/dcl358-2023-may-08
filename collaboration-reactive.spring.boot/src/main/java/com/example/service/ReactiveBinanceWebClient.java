package com.example.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.dto.TickerDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
public class ReactiveBinanceWebClient {
	private static final String BINANCE_BASE_URL = "https://api.binance.com/api/v3/ticker";
	private static final List<String> SYMBOLS = List.of(
			"BTCUSDT", "LTCBTC", "BNBBTC", "NEOBTC", "EOSETH",
			"SNTETH","BNTETH","BCCBTC","SALTBTC","SALTETH","XVGETH",
			"XVGBTC", "SUBETH","EOSBTC","MTHBTC","ETCETH","DNTBTC","ENGBTC"
			
	);
	private static final Comparator<TickerDTO> BY_PRICE =
			(t1,t2) -> Double.parseDouble(t1.getPrice()) <= Double.parseDouble(t2.getPrice()) ?	
				-1 : 1 ;	
	private WebClient webClient = WebClient.builder().baseUrl(BINANCE_BASE_URL).build();
	
	//@Scheduled(fixedRate=5_000)
	public void reactiveCallToBinance() {
		Flux.fromIterable(SYMBOLS)
	    .parallel()
	    .runOn(Schedulers.boundedElastic()) // like Executors.newFixedThreadPool(8)
	    .flatMap(this::getTickerDTO)
	    .sorted(BY_PRICE)
	    //.doOnError(System.err::println)
	    .subscribe(ticker -> {
	    	System.out.println(Thread.currentThread().getName()+": "+ticker);
	    });		
	}
	
	public Mono<TickerDTO> getTickerDTO(String symbol) { // async
		System.out.println(Thread.currentThread().getName()+" is sending the request to "+symbol);
		return webClient.get() // IO Bound
				        .uri(uriBuilder -> uriBuilder.path("/price")
				        		            .queryParam("symbol", symbol).build())
				        .retrieve()
				        .bodyToMono(TickerDTO.class);
	}
}
