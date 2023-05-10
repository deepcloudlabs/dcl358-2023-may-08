package com.example.crm.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.example.crm.domain.Customer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReactiveCustomerRepository extends ReactiveMongoRepository<Customer, String>{
	Mono<Customer> findOneByEmail(String email);
	Flux<Customer> findAllByAddressesCity(String city);
	@Query("{}")
	Flux<Customer> find(PageRequest page);
}
