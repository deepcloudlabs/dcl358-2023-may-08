package com.example.service;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

@Service
public class CoreService {

	@Async("tp1")
	public Future<Integer> fun() throws InterruptedException {
		System.err.println("[%s] runs CoreService::fun()".formatted(Thread.currentThread().getName()));
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			System.err.println("Error: %s".formatted(e.getMessage()));
		}
		return new AsyncResult<Integer>(42);
	}
	
	@Async("tp1")
	public Future<Integer> gun() throws InterruptedException {
		System.err.println("[%s] runs CoreService::fun()".formatted(Thread.currentThread().getName()));
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			System.err.println("Error: %s".formatted(e.getMessage()));
		}
		return new AsyncResult<Integer>(42);
	}

}
