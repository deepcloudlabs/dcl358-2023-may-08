package com.example.crm.service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import org.springframework.stereotype.Service;

import com.example.crm.domain.EventDocument;

@Service
@ConditionalOnProperty(name = "kafkaClient", havingValue = "reactive")
public class ReactiveKafkaMessagingService  implements MessagingService {
	private final ReactiveKafkaProducerTemplate<String, String> kafkaTemplate;

	public ReactiveKafkaMessagingService(ReactiveKafkaProducerTemplate<String, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}



	@Override
	public void sendMessage(EventDocument event) {
		kafkaTemplate.send(event.getTopic(), event.getMessage()).subscribe();
	}
}
