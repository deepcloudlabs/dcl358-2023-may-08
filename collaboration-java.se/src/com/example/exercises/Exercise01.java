package com.example.exercises;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;

public class Exercise01 {
	private static final String BINANCE_API = "https://api.binance.com/api/v3/ticker/price?symbol=BTCUSDT";

	public static void main(String[] args) throws IOException, InterruptedException {
		var httpClient = HttpClient.newHttpClient();
		var httpRequest = HttpRequest.newBuilder(URI.create(BINANCE_API)).build();
		var begin = System.currentTimeMillis();
		for(var i=0;i<10;++i) {
			// Query -> Response
			var response = httpClient.send(httpRequest, BodyHandlers.ofString());
			System.out.println(response.body());			
		}
		var end = System.currentTimeMillis();
		System.err.println("Duration: %d ms.".formatted(end-begin));
	}

}
