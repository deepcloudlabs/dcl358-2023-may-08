package com.example.ecommerce.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data()
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class OrderCancelledEvent extends OrderBaseEvent {
}
