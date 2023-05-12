package com.example.ecommerce.dto.output;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderResponse {
	private int orderId;
	private String status;
	
}
