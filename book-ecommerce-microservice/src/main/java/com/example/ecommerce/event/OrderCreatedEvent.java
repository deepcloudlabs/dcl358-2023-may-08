package com.example.ecommerce.event;

import java.util.List;

import com.example.ecommerce.entity.write.BookItem;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class OrderCreatedEvent extends OrderBaseEvent {
	private List<BookItem> items;
}
