package com.example.crm.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.crm.domain.Customer;
import com.example.crm.service.ReactiveCustomerService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customers")
@CrossOrigin
public class ReactiveCustomerController {
	private final ReactiveCustomerService customerService;
	
	public ReactiveCustomerController(ReactiveCustomerService customerService) {
		this.customerService = customerService;
	}

	// Query
	@GetMapping("/{identity}")
	public Mono<Customer> getCustomerByIdentity(@PathVariable String identity) {
		return customerService.findCustomerById(identity);	
	}

	@GetMapping(params = { "page", "size" })
	public Flux<Customer> getCustomers(@RequestParam int page, @RequestParam int size) {
		return customerService.findCustomerByPage(page,size);	
	}

	// Command
	@PostMapping
	public Mono<Customer> acquireCustomer(@RequestBody Customer customer) {
		return customerService.addCustomer(customer);	
	}

	@PutMapping("/{identity}")
	public Mono<Customer> updateCustomer(@PathVariable String identity,@RequestBody Customer customer) {
		return customerService.updateCustomer(identity,customer);	
	}

	@DeleteMapping("/{identity}")
	public Mono<Customer> releaseCustomer(@PathVariable String identity) {
		return customerService.removeCustomerById(identity);	
	}
	
}
