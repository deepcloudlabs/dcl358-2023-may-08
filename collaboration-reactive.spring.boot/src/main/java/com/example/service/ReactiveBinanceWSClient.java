package com.example.service;

import java.net.URI;

import javax.annotation.PostConstruct;

import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.client.WebSocketClient;

//@Service
public class ReactiveBinanceWSClient {
	private static final String BINANCE_WS_API = 
			"wss://stream.binance.com:9443/ws/btcusdt@trade";
	private final WebSocketClient webSocketClient;
	
	public ReactiveBinanceWSClient(WebSocketClient webSocketClient) {
		this.webSocketClient = webSocketClient;
	}

	@PostConstruct
	public void connect() {
		webSocketClient.execute(URI.create(BINANCE_WS_API), session -> {
			System.err.println("Connected to binance ws server.");
			return session.receive().map(WebSocketMessage::getPayloadAsText).doOnNext(trade -> System.err.println(trade)).then();
		}).block();
	}
}
