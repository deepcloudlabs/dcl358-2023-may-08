package com.example.crm.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.example.crm.domain.EventDocument;

import reactor.core.publisher.Flux;

public interface ReactiveEventOutboxRepository extends ReactiveMongoRepository<EventDocument, String> {
	@Query("{}")
	Flux<EventDocument> find(PageRequest page);
}
