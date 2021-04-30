package com.nab.product.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ProductTracking implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@JsonProperty("product_id")
	private Long productID;

	@JsonProperty("user_email")
	private String userEmail;
	
	@JsonProperty("product_view")
	private ProductView productView;
}
