package com.nab.cart.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Document("cart")
public class Cart extends AbstractEntity{
private static final long serialVersionUID = 1L;
	
	@Id
	@JsonProperty("id")
	private String id;
	
	@JsonProperty("product_items")
	@Field("product_items")
	private List<Product> productItems;
	
	@JsonProperty("user_email")
	@Field("user_email")
	private String userEmail;
	
	@JsonProperty("cart_name")
	@Field("cart_name")
	private String cartName;
	
}
