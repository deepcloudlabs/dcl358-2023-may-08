package com.example.ecommerce.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.ecommerce.event.OrderBaseEvent;

public interface OrderEventRepository extends MongoRepository<OrderBaseEvent, Integer> {

}
