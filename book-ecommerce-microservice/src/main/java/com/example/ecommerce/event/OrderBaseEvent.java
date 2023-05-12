package com.example.ecommerce.event;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.Data;

@Data
@Document(collection="order_events" )
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "eventType")
@JsonSubTypes({
  @Type(value=OrderCreatedEvent.class),	
  @Type(value=OrderCancelledEvent.class)	
})
public abstract class OrderBaseEvent {
	@Id
	private String eventId = UUID.randomUUID().toString();
	private int orderId;
	private int userId;
}
