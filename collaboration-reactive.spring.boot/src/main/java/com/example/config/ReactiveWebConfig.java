package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import org.springframework.web.reactive.socket.client.WebSocketClient;

@Configuration
public class ReactiveWebConfig {

	@Bean
	WebSocketClient createWebSocketClient() {
		return new ReactorNettyWebSocketClient();
	}
}
