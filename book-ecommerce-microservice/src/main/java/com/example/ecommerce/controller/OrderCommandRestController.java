package com.example.ecommerce.controller;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.dto.output.OrderResponse;
import com.example.ecommerce.entity.write.OrderBaseCommand;
import com.example.ecommerce.service.OrderCommandService;

@RestController
@RequestMapping("/commands")
@Validated
@CrossOrigin
@ConditionalOnProperty(name = "enable.write", havingValue = "true")
public class OrderCommandRestController {
	private final OrderCommandService orderCommandService;

	public OrderCommandRestController(OrderCommandService orderCommandService) {
		this.orderCommandService = orderCommandService;
	}

	@PostMapping
	public OrderResponse createOrder(@RequestBody OrderBaseCommand command) {
		return orderCommandService.handle(command);
	}

}
