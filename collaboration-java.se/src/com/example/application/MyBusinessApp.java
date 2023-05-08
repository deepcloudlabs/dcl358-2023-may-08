package com.example.application;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import com.example.service.CoreService;

public class MyBusinessApp {

	public static void main(String[] args) throws InterruptedException, IOException {
		final ExecutorService pool = Executors.newFixedThreadPool(200);
		Consumer<Integer> observer = result -> System.err
				.println("[%s] Result is %d".formatted(Thread.currentThread().getName(), result));
		try (Closeable close = pool::shutdown;) {
			var coreService = new CoreService(pool);
			System.err.println("Application is just started!");
			for (var i = 0; i < 100; ++i) {
				System.err.println("Before calling the fun method...");
				// asynchronous -> non-blocking
				// observer pattern --> "system" event
				coreService.fun().thenAcceptAsync(observer, pool);
				System.err.println("After the fun method returns...");
			}
			TimeUnit.SECONDS.sleep(100);
		}
		System.err.println("Application is just ended!");
	}

}
