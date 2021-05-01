package com.nab.order.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
	
	private Long id;

	@NotNull
	@JsonProperty("user_email")
	private String userEmail;

	@NotNull	
	@JsonProperty("cart_id")
	private String cartId;
	
	@JsonProperty("product_items")
	private List<ProductDTO> productItems;
	
	@Column(name = "total_price")
	@JsonProperty("total_price")
	private BigDecimal totalPrice;
}
