package com.example.ecommerce.entity.read;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.example.ecommerce.entity.write.BookItem;

import lombok.Data;

@Data
@Document(collection="orders")
public class Order {
	@Id
	private int orderId;
	@Field("user_id")
	private int userId;
	private double total;
	private List<BookItem> items;
	
	
}
