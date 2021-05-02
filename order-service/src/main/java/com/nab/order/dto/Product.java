package com.nab.order.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

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
	@NotNull
	private Long id;

	@JsonProperty("product_name")
	private String productName;

	private BigDecimal price;

	private int availability;

	private String brand;

	private String color;

	private String description;
}
