package com.nab.order.converter;

import com.nab.order.dto.ProductDTO;
import com.nab.order.model.ProductDetail;

/**
 * Converts ProductDTO to Product Entity.
 * Converts Product entity to ProductDTO
 */
public class ProductConverter extends BaseConverter<ProductDTO, ProductDetail> {

	private static ProductConverter instance = new ProductConverter();

	public static ProductConverter getInstance() {
		return ProductConverter.instance;
	}

	private ProductConverter() {
		super(productDTO -> new ProductDetail(
				productDTO.getId(),
				productDTO.getProductId(),
				productDTO.getProductName(),
				productDTO.getPrice(),
				productDTO.getAvailability(),
				productDTO.getBrand(),
				productDTO.getColor(),
				productDTO.getDescription())
				,
			product -> new ProductDTO(
					product.getId(),
					product.getProductId(),
					product.getProductName(),
					product.getPrice(),
					product.getAvailability(),
					product.getBrand(),
					product.getColor(),
					product.getDescription()));
	}
}
