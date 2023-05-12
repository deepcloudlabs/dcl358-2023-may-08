package com.example.crm.service;

import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.crm.domain.EventDocument;
import com.example.crm.repository.ReactiveEventOutboxRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class OutboxService {
	private final MessagingService messagingService;
	private final ReactiveEventOutboxRepository reactiveEventOutboxRepository;
	private final ObjectMapper objectMapper;

	public OutboxService(MessagingService messagingService, ObjectMapper objectMapper, ReactiveEventOutboxRepository reactiveEventOutboxRepository) {
		this.messagingService = messagingService;
		this.reactiveEventOutboxRepository = reactiveEventOutboxRepository;
		this.objectMapper = objectMapper;
	}

	public void sendMessage(String topic,Object event) {
		try {
			var eventDocument= new EventDocument();
			eventDocument.setId(UUID.randomUUID().toString());
			eventDocument.setTopic(topic);
			eventDocument.setMessage(objectMapper.writeValueAsString(event));
			reactiveEventOutboxRepository.insert(eventDocument).subscribe();
		}catch(JsonProcessingException e) {
			System.err.println("Error: %s".formatted(e.getMessage()));
		}
	}
	
	@Scheduled(fixedRate = 10_000)
	public void sendMessagesInOutbox() {
		reactiveEventOutboxRepository.find(PageRequest.of(0, 10))
		                             .doOnNext(messagingService::sendMessage)
		                             .doOnNext(eventDocument->reactiveEventOutboxRepository.delete(eventDocument).subscribe())
		                             .subscribe(System.err::println);
	}
}
