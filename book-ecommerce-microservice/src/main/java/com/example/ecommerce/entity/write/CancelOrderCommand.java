package com.example.ecommerce.entity.write;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class CancelOrderCommand extends OrderBaseCommand {
	private int userIdentity;
	
}
