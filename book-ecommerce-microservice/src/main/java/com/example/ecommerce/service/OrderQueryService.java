package com.example.ecommerce.service;

import org.springframework.stereotype.Service;

import com.example.ecommerce.entity.read.Order;
import com.example.ecommerce.repository.OrderRepository;

@Service
public class OrderQueryService {
	private final OrderRepository orderRepository;
	
	public OrderQueryService(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	public Order findOrderById(int orderId) {
		return orderRepository.findById(orderId).orElseThrow(() -> new IllegalArgumentException("Cannot find order!"));
	}

}
