package com.nab.order.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

	@JsonProperty("id")
	private Long id;

	@NotNull
	@JsonProperty("product_id")
	private Long productId;

	@JsonProperty("product_name")
	private String productName;

	@NotNull
	private BigDecimal price;

	@NotNull
	private int availability;

	private String brand;

	private String color;

	private String description;

	public ProductDTO(@NotNull Long productId, String productName, @NotNull BigDecimal price,
			@NotNull int availability, String brand, String color, String description) {
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.availability = availability;
		this.brand = brand;
		this.color = color;
		this.description = description;
	}
	
}
