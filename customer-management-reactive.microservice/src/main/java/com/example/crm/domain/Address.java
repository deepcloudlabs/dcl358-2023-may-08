package com.example.crm.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "addressId")
public class Address {
	private String addressId;
	private String city;
	private String country;
	private String street;
	private String zipCode;
}
