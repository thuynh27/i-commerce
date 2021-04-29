package com.nab.product.converter;

import com.nab.product.dto.ProductDTO;
import com.nab.product.model.Product;

/**
 * Converts ProductDTO to Product Entity.
 * Converts Product entity to ProductDTO
 */
public class ProductConverter extends BaseConverter<ProductDTO, Product> {

	private static ProductConverter instance = new ProductConverter();

	public static ProductConverter getInstance() {
		return ProductConverter.instance;
	}

	private ProductConverter() {
		super(productDTO -> new Product(
				productDTO.getId(),
				productDTO.getProductName(),
				productDTO.getPrice(),
				productDTO.getAvailability(),
				productDTO.getBrand(),
				productDTO.getColor(),
				productDTO.getDescription(),
				productDTO.getImageUrl())
				,
			product -> new ProductDTO(
					product.getId(),
					product.getProductName(),
					product.getPrice(),
					product.getAvailability(),
					product.getBrand(),
					product.getColor(),
					product.getDescription(),
					product.getImageUrl()));
	}
}
