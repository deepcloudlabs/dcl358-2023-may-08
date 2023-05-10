package com.example.service;

import java.util.concurrent.ExecutionException;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class AnotherService {
	private final CoreService coreService;
	
	public AnotherService(CoreService coreService) {
		this.coreService = coreService;
	}

	@Scheduled(fixedRate = 3_000)
	public void callFun() throws InterruptedException, ExecutionException {
		var result = coreService.fun().get();
		System.err.println("Result is %d.".formatted(result));
	}
}
