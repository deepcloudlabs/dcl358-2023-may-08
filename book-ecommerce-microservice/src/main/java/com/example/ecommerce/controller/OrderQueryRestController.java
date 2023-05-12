package com.example.ecommerce.controller;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.entity.read.Order;
import com.example.ecommerce.service.OrderQueryService;

@RestController
@RequestMapping("/orders")
@Validated
@CrossOrigin
@ConditionalOnProperty(name="enable.read", havingValue = "true")
public class OrderQueryRestController {
	private final OrderQueryService orderQueryService;
	
	public OrderQueryRestController(OrderQueryService orderQueryService) {
		this.orderQueryService = orderQueryService;
	}

	@GetMapping("{orderId}")
	public Order getOrderById(@PathVariable int orderId) {
		return orderQueryService.findOrderById(orderId);
	}
}
