package com.example.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommerce.entity.write.OrderBaseCommand;

public interface OrderCommandRepository extends JpaRepository<OrderBaseCommand, Integer> {

}
