package com.example.crm.service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.crm.domain.EventDocument;

@Service
@ConditionalOnProperty(name = "kafkaClient", havingValue = "blocking")
public class KafkaMessagingService implements MessagingService {
	private final KafkaTemplate<String, String> kafkaTemplate;

	public KafkaMessagingService(KafkaTemplate<String, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	@Override
	public void sendMessage(EventDocument event) {
		kafkaTemplate.send(event.getTopic(), event.getMessage());
	}
}
