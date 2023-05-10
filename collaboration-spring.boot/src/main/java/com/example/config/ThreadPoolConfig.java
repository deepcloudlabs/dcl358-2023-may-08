package com.example.config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
public class ThreadPoolConfig {

	@Bean("tp1")
	ExecutorService heavyThreadPool() {
		return Executors.newFixedThreadPool(50);
	}
}
