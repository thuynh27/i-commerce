package com.nab.product.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
	
	private Long id;
	
	@JsonProperty("product_name")
    private String productName;

    private BigDecimal price;

    private int availability;

    private String brand;
    
    private String color;

    private String description;
    
	@JsonProperty("image_url")
    private String imageUrl;
}
