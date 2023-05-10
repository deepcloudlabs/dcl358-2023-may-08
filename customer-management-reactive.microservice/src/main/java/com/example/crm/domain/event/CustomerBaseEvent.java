package com.example.crm.domain.event;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "eventId")
public abstract class CustomerBaseEvent {
	private final String eventId = UUID.randomUUID().toString();
	private final LocalDateTime eventDateTime = LocalDateTime.now();
	private final String customerId;
	private final CustomerEventType eventType;
	private final String version = "0.0.1";

}
