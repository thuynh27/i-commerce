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
	private String productId;

	@JsonProperty("product_name")
	private String productName;

	@NotNull
	private BigDecimal price;

	@NotNull
	private int availability;

	private String brand;

	private String color;

	private String description;
}
