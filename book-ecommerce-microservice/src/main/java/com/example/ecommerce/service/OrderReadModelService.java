package com.example.ecommerce.service;

import java.util.List;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.ecommerce.entity.read.Order;
import com.example.ecommerce.entity.write.BookItem;
import com.example.ecommerce.event.OrderBaseEvent;
import com.example.ecommerce.event.OrderCreatedEvent;
import com.example.ecommerce.repository.OrderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@ConditionalOnProperty(name = "enable.read", havingValue = "true")
public class OrderReadModelService {
	private final ObjectMapper objectMapper;
	private final OrderRepository orderRepository;
	public OrderReadModelService(ObjectMapper objectMapper, OrderRepository orderRepository) {
		this.objectMapper = objectMapper;
		this.orderRepository = orderRepository;
	}

	@KafkaListener(topics = {"order-events"})
	public void readEvents(String eventAsJson) throws JsonMappingException, JsonProcessingException {
		var event = objectMapper.readValue(eventAsJson, OrderBaseEvent.class);
		System.err.println(event);
		if (event instanceof OrderCreatedEvent orderCreatedEvent) {
			var order = new Order();
			order.setOrderId(orderCreatedEvent.getOrderId());
			order.setUserId(orderCreatedEvent.getUserId());
			List<BookItem> items = orderCreatedEvent.getItems();
			order.setItems(items);
			order.setTotal(items.stream().mapToDouble(item->item.getPrice()*item.getQuantity()).sum());
			orderRepository.save(order);
		}
	}
}
