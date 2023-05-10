package com.example.crm.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.crm.domain.Customer;
import com.example.crm.domain.event.AddressChangedEvent;
import com.example.crm.repository.ReactiveCustomerRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ReactiveCustomerService {
	private final ReactiveCustomerRepository reactiveCustomerRepository;
	private final OutboxService outboxService;
	
	public ReactiveCustomerService(ReactiveCustomerRepository reactiveCustomerRepository, OutboxService outboxService) {
		this.reactiveCustomerRepository = reactiveCustomerRepository;
		this.outboxService = outboxService;
	}

	// Query
	public Mono<Customer> findCustomerById(String identity) {
		return reactiveCustomerRepository.findById(identity);
	}

	public Flux<Customer> findCustomerByPage(int page, int size) {
		return reactiveCustomerRepository.find(PageRequest.of(page, size));
	}

	// Command
	public Mono<Customer> addCustomer(Customer customer) {
		return reactiveCustomerRepository.insert(customer);
	}

	@Transactional
	public Mono<Customer> updateCustomer(String identity, Customer customer) {
		findCustomerById(identity).doOnNext( customerFound -> {
			reactiveCustomerRepository.save(customer).doOnNext(cust -> {
				if(!customerFound.getAddresses().containsAll(cust.getAddresses())) {
					var addressChangedEvent = new AddressChangedEvent(identity, customerFound.getAddresses(), customer.getAddresses());
					outboxService.sendMessage("crm-events", addressChangedEvent);
				}					
			}).subscribe();
		}).subscribe();
		return Mono.just(customer);
		
	}

	public Mono<Customer> removeCustomerById(String identity) {
		return reactiveCustomerRepository.findById(identity)
				   .doOnNext( customer -> {
					   reactiveCustomerRepository.delete(customer).subscribe();
				   } );
	}

}
