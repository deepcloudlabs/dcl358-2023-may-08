package com.example.service;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketClient;

@Service
public class Exercise05 implements WebSocketHandler {
	private static final String BINANCE_WS_API = 
			"wss://stream.binance.com:9443/ws/btcusdt@trade";
	private final WebSocketClient webSocketClient;
	
	public Exercise05(WebSocketClient webSocketClient) {
		this.webSocketClient = webSocketClient;
	}

	@PostConstruct
	public void connect() {
		webSocketClient.doHandshake(this, BINANCE_WS_API);
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.err.println("Connected to the binance ws server");
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		System.err.println(message.getPayload().toString());
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable e) throws Exception {
		System.err.println("Error: %s.".formatted(e.getMessage()));
		
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		System.err.println("Connection is closed!");
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}

}
