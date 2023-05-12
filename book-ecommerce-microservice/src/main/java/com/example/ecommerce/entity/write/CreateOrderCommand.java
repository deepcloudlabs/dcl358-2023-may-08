package com.example.ecommerce.entity.write;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class CreateOrderCommand extends OrderBaseCommand {
	private int userIdentity;
	@OneToMany(cascade = CascadeType.ALL)
	private List<BookItem> items;
}
