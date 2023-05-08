package com.example.service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class CoreService {
	private final ExecutorService executorService;
	
	public CoreService(ExecutorService executorService) {
		this.executorService = executorService;
	}

	public CompletableFuture<Integer> fun() throws InterruptedException {
		return CompletableFuture.supplyAsync(() -> {
			System.err.println("[%s] Running fun()...".formatted(Thread.currentThread().getName()));
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				System.err.println("Error: %s".formatted(e.getMessage()));
			}
			return 42;
		}, executorService);
	}

	public int gun() {
		return 42;
	}
}
