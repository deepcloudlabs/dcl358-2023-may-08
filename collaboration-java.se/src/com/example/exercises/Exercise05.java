package com.example.exercises;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.net.http.WebSocket.Listener;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

public class Exercise05 {
	private static final String BINANCE_WS_API = 
			"wss://stream.binance.com:9443/ws/btcusdt@trade";
	
	public static void main(String[] args) throws InterruptedException {
		var binanceWSListener = new BinanceWebSocketListener();
		HttpClient.newHttpClient()
				  .newWebSocketBuilder()
				  .buildAsync(URI.create(BINANCE_WS_API), binanceWSListener);
		TimeUnit.SECONDS.sleep(30);
	}

}
class BinanceWebSocketListener implements WebSocket.Listener {

	@Override
	public void onOpen(WebSocket webSocket) {
		System.err.println("Successfully connected to the binance ws server.");
		webSocket.request(1);
	}

	@Override
	// Event
	public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
		System.err.println(data);
		webSocket.request(1);
		return null;
	}

	@Override
	public CompletionStage<?> onClose(WebSocket webSocket, int statusCode, String reason) {
		System.err.println("Disconnected from the binance ws server.");
		return null;
	}

	@Override
	public void onError(WebSocket webSocket, Throwable error) {
		System.err.println("An error has occurred: %s".formatted(error.getMessage()));
		Listener.super.onError(webSocket, error);
	}
	
}

