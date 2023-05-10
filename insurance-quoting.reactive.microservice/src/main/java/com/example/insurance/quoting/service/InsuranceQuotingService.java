package com.example.insurance.quoting.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class InsuranceQuotingService {

	@KafkaListener(topics="crm-events")
	public void listenEvents(String event) {
		System.err.println("New crm event has arrived: %s".formatted(event));
	}
}
