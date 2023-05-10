package com.example.crm.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.crm.domain.EventDocument;

@Service
public class KafkaMessagingService {
	private final KafkaTemplate<String, String> kafkaTemplate;

	public KafkaMessagingService(KafkaTemplate<String, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	public void sendMessage(EventDocument event) {
		kafkaTemplate.send(event.getTopic(), event.getMessage());
	}
}
