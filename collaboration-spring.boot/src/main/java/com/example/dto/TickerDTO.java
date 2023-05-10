package com.example.dto;

import lombok.Data;

// {"symbol":"BTCUSDT","price":"27561.59000000"}
@Data
public class TickerDTO {
	private String symbol;
	private String price;
}
