package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

@Configuration
@SuppressWarnings("deprecation")
public class WebConfig {

	@Bean
	RestTemplate createRestTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	WebSocketClient createWebSocketClient() {
		return new StandardWebSocketClient();
	}
	
	@Bean
	AsyncRestTemplate createAsyncRestTemplate() {
		return new AsyncRestTemplate();
	}
}
