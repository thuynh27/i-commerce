package com.nab.order.converter;

import com.nab.order.dto.Product;
import com.nab.order.dto.ProductDTO;

/**
 * Converts ProductDTO to Product Entity.
 * Converts Product entity to ProductDTO
 */
public class CartProductConverter extends BaseConverter<ProductDTO, Product> {

	private static CartProductConverter instance = new CartProductConverter();

	public static CartProductConverter getInstance() {
		return CartProductConverter.instance;
	}

	private CartProductConverter() {
		super(productDTO -> new Product(
				productDTO.getProductId(),
				productDTO.getProductName(),
				productDTO.getPrice(),
				productDTO.getAvailability(),
				productDTO.getBrand(),
				productDTO.getColor(),
				productDTO.getDescription())
				,
			product -> new ProductDTO(product.getId(),
					product.getProductName(),
					product.getPrice(),
					product.getAvailability(),
					product.getBrand(),
					product.getColor(),
					product.getDescription()));
	}
}
