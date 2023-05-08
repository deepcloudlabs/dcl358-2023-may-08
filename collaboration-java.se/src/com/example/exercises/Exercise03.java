package com.example.exercises;
import java.util.List;
import java.util.Observable;
import java.util.concurrent.TimeUnit;

import com.example.event.TradeEvent;

@SuppressWarnings({ "deprecation" })
public class Exercise03 {

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
		var observable = new TradeObservable();
		observable.addObserver((o,event)->{
			try {TimeUnit.SECONDS.sleep(5);} catch (InterruptedException e) {}
			System.err.println("Slow observer successfully processed the event: %s".formatted(event));
		});
		observable.addObserver((o,event)->{
			System.err.println("Fast observer successfully processed the event: %s".formatted(event));
		});
		trades.forEach(observable::notifyObservers);
		System.err.println("Application is done.");
	}

}

@SuppressWarnings("deprecation")
class TradeObservable extends Observable {

	@Override
	public void notifyObservers(Object event) {
		super.setChanged();
		super.notifyObservers(event);
	}
	
}