package com.example.service;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

import com.example.dto.TickerDTO;

@Service
@SuppressWarnings({ "deprecation" })
public class Exercise01 {
	private static final String BINANCE_API = "https://api.binance.com/api/v3/ticker/price?symbol=BTCUSDT";
	private final RestTemplate restTemplate;
	private final AsyncRestTemplate asyncRestTemplate;
	
	public Exercise01(RestTemplate restTemplate,AsyncRestTemplate asyncRestTemplate) {
		this.restTemplate = restTemplate;
		this.asyncRestTemplate = asyncRestTemplate;
	}

	//@Scheduled(fixedRate = 5_000)
	public void fun() throws IOException, InterruptedException {
		var begin = System.currentTimeMillis();
		for(var i=0;i<10;++i) {
			// Query -> Response
			var ticker = restTemplate.getForEntity(BINANCE_API, TickerDTO.class).getBody();
			System.out.println(ticker);			
		}
		var end = System.currentTimeMillis();
		System.err.println("Duration: %d ms.".formatted(end-begin));
	}
	
	@Scheduled(fixedRate = 5_000)
	public void gun() throws IOException, InterruptedException {
		var counter = new AtomicInteger(0);
		var begin = System.currentTimeMillis();
		for(var i=0;i<10;++i) {
			// Query -> Response
			asyncRestTemplate.getForEntity(BINANCE_API, TickerDTO.class)
		            .addCallback(
			   /* success */	ticker -> {
				   System.err.println(ticker.getBody());
				   if(counter.incrementAndGet() == 10) {
					   var end = System.currentTimeMillis();
					   System.err.println("Duration: %d ms.".formatted(end-begin));
				   }
			   },
			   /* failure */  	err -> System.err.println(err)
		    );
		}
	}

}
