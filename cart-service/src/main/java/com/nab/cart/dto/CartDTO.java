package com.nab.cart.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nab.cart.model.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {

	private String id;
	
	@JsonProperty("product_items")
	private List<Product> productItems;
	
	@JsonProperty("user_email")
	@NotNull
	private String userEmail;
	
	@JsonProperty("cart_name")
	private String cartName;
}
