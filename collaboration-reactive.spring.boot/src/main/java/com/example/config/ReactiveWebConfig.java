package com.example.config;

import java.util.HashMap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import org.springframework.web.reactive.socket.client.WebSocketClient;

@Configuration
public class ReactiveWebConfig {

	@Bean
	WebSocketClient createWebSocketClient() {
		return new ReactorNettyWebSocketClient();
	}
	
	@Bean
	HandlerMapping websocketHandlerMapping(WebSocketHandler webSocketHandler) {
		var map = new HashMap<String,WebSocketHandler>();
		map.put("/changes", webSocketHandler);
		var handlerMapping = new SimpleUrlHandlerMapping();
		handlerMapping.setOrder(1);
		handlerMapping.setUrlMap(map);
		return handlerMapping;
	}
}
