package com.example.exercises;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Exercise02 {
	private static final String BINANCE_API = "https://api.binance.com/api/v3/ticker/price?symbol=BTCUSDT";
	private static final AtomicInteger counter = new AtomicInteger();
	public static void main(String[] args) throws InterruptedException {
		var httpClient = HttpClient.newHttpClient();
		var httpRequest = HttpRequest.newBuilder(URI.create(BINANCE_API)).build();
		var begin = System.currentTimeMillis();
		for(var i=0;i<10;++i) {
			// Query --> Response -> Observer
			httpClient.sendAsync(httpRequest, BodyHandlers.ofString())
			          .thenAcceptAsync(res -> {
			        	  System.err.println(res.body());
			        	  if(counter.incrementAndGet()== 10) {
			        		  var end = System.currentTimeMillis();
			        		  System.err.println("Duration: %d ms.".formatted(end-begin));			        		  
			        	  }
			          });
		}
		TimeUnit.SECONDS.sleep(10);
	}

}
