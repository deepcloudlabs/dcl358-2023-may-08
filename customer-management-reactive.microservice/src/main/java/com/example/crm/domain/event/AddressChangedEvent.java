package com.example.crm.domain.event;

import java.util.List;

import com.example.crm.domain.Address;

import lombok.Getter;

@Getter
public class AddressChangedEvent extends CustomerBaseEvent {
	private final List<Address> oldAddresses;
	private final List<Address> newAddresses;

	public AddressChangedEvent(String customerId, List<Address> oldAddresses, List<Address> newAddresses) {
		super(customerId, CustomerEventType.ADDRESS_CHANGED_EVENT);
		this.oldAddresses = oldAddresses;
		this.newAddresses = newAddresses;
	}

}
