package com.nab.cart.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("product_id")
	@Field("product_id")
	@NotNull
	private Long id;

	@JsonProperty("product_name")
	@Field("product_name")
	private String productName;

	private BigDecimal price;

	private int availability;

	private String brand;

	private String color;

	private String description;
}
