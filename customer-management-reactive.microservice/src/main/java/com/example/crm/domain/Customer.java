package com.example.crm.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Document(collection="customers")
@EqualsAndHashCode(of = "identity")
public class Customer {
	@Id
	private String identity;
	private String fullName;
	private String email;
	private String sms;
	private List<Address> addresses = new ArrayList<>();
}
