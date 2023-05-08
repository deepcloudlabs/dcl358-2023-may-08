package com.example.exercises;
import java.util.List;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

import com.example.event.TradeEvent;

public class Exercise04 {

	public static void main(String[] args) {
		System.err.println("Application is just started.");
		var trades = List.of(
				new TradeEvent("orcl", 100, 100),
				new TradeEvent("ibm", 120, 700),
				new TradeEvent("msft", 130, 100),
				new TradeEvent("orcl", 110, 200),
				new TradeEvent("ibm", 150, 400),
				new TradeEvent("msft", 140, 300)
		);
		try(
				var publisher = new SubmissionPublisher<TradeEvent>();
				) {
			publisher.subscribe(new SlowSubscriber());
			publisher.subscribe(new FastSubscriber());
			trades.forEach(publisher::submit);
		}
		System.err.println("Application is done.");
		try {TimeUnit.SECONDS.sleep(30);} catch (InterruptedException e) {}
	}

}

class SlowSubscriber implements Subscriber<TradeEvent> {
	
	private Subscription subscription;

	@Override
	public void onSubscribe(Subscription subscription) {
		this.subscription = subscription;
		System.err.println("SlowSubscriber has successfully subscribed!");
		this.subscription.request(1);
	}

	@Override
	public void onNext(TradeEvent event) {
		//try {TimeUnit.SECONDS.sleep(3);} catch (InterruptedException e) {}
		System.err.println("[%s] SlowSubscriber has successfully processed the event: %s".formatted(Thread.currentThread().getName(), event));
		this.subscription.request(1);		
	}

	@Override
	public void onError(Throwable e) {
		System.err.println("SlowSubscriber has failed while processing the event: %s".formatted(e.getMessage()));
	}

	@Override
	public void onComplete() {
		System.err.println("SlowSubscriber has successfully completed!");
	}
	
}

class FastSubscriber implements Subscriber<TradeEvent> {
	private Subscription subscription;
	
	@Override
	public void onSubscribe(Subscription subscription) {
		this.subscription = subscription;
		System.err.println("FastSubscriber has successfully subscribed!");
		this.subscription.request(1);
	}
	
	@Override
	public void onNext(TradeEvent event) {
		System.err.println("[%s] FastSubscriber has successfully processed the event: %s".formatted(Thread.currentThread().getName(),event));
		this.subscription.request(1);
	}
	
	@Override
	public void onError(Throwable e) {
		System.err.println("FastSubscriber has failed while processing the event: %s".formatted(e.getMessage()));
	}
	
	@Override
	public void onComplete() {
		System.err.println("FastSubscriber has successfully completed!");
	}
	
}
