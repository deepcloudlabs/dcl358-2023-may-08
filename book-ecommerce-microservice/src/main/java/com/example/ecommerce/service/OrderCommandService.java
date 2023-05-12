package com.example.ecommerce.service;

import javax.transaction.Transactional;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.ecommerce.dto.output.OrderResponse;
import com.example.ecommerce.entity.write.CancelOrderCommand;
import com.example.ecommerce.entity.write.CreateOrderCommand;
import com.example.ecommerce.entity.write.OrderBaseCommand;
import com.example.ecommerce.event.OrderBaseEvent;
import com.example.ecommerce.event.OrderCancelledEvent;
import com.example.ecommerce.event.OrderCreatedEvent;
import com.example.ecommerce.repository.OrderCommandRepository;
import com.example.ecommerce.repository.OrderEventRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@ConditionalOnProperty(name = "enable.write", havingValue = "true")
public class OrderCommandService {
	private final OrderCommandRepository orderCommandRepository;
	private final OrderEventRepository orderEventRepository;
	private final KafkaTemplate<String, String> kafkaTemplate;
	private final ObjectMapper objectMapper;

	public OrderCommandService(OrderCommandRepository orderCommandRepository,
			OrderEventRepository orderEventRepository, KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
		this.orderCommandRepository = orderCommandRepository;
		this.orderEventRepository = orderEventRepository;
		this.kafkaTemplate = kafkaTemplate;
		this.objectMapper = objectMapper;
	}

	@Transactional
	public OrderResponse handle(OrderBaseCommand command) {
		OrderBaseEvent event= null;
		orderCommandRepository.save(command);
		
		if (command instanceof CreateOrderCommand createOrderCommand) {
			var orderCreatedEvent = new OrderCreatedEvent();
			event = orderCreatedEvent;
			orderCreatedEvent.setItems(createOrderCommand.getItems());
			orderCreatedEvent.setUserId(createOrderCommand.getUserIdentity());
			orderCreatedEvent.setOrderId(createOrderCommand.getOrderId());
			orderEventRepository.insert(orderCreatedEvent);
		} else if (command instanceof CancelOrderCommand cancelOrderCommand) {
			var orderCancelledEvent = new OrderCancelledEvent();
			orderCancelledEvent.setOrderId(cancelOrderCommand.getOrderId());
			orderCancelledEvent.setUserId(cancelOrderCommand.getUserIdentity());
			event = orderCancelledEvent;
			orderEventRepository.insert(orderCancelledEvent);
		}
		try {
			kafkaTemplate.send("order-events", objectMapper.writeValueAsString(event));
		} catch (JsonProcessingException e) {
			System.err.println("Error: %s".formatted(e.getMessage()));
		}
		return new OrderResponse(command.getOrderId(), "success");
	}

}
