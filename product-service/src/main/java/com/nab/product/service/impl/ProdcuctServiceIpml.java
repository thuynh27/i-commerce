package com.nab.product.service.impl;

import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nab.product.converter.ProductConverter;
import com.nab.product.dto.ProductDTO;
import com.nab.product.exception.BadRequestException;
import com.nab.product.model.Product;
import com.nab.product.model.ProductHistory;
import com.nab.product.repository.ProductHistoryRepository;
import com.nab.product.repository.ProductRepository;
import com.nab.product.service.ProductService;

@Service
public class ProdcuctServiceIpml implements ProductService {

	private ProductRepository productRepository;

	private ProductHistoryRepository productHistoryRepository;

	@Autowired
	public ProdcuctServiceIpml(ProductRepository productRepository , ProductHistoryRepository productHistoryRepository) {
		this.productRepository = productRepository;
		this.productHistoryRepository = productHistoryRepository;
	}

	@Override
	@Transactional
	public Page<ProductDTO> getAllProduct(Pageable pageable) {
		return productRepository.findAll(pageable).map(ProductConverter.getInstance()::convertFromEntity);
	}

	@Override
	@Transactional
	public Page<ProductDTO> getAllProductByCategory(String category, Pageable pageable) {
		return null;
	}

	@Override
	@Transactional
	public ProductDTO getProductById(Long id) {
		return ProductConverter.getInstance().convertFromEntity(getProduct(id));
	}

	@Override
	@Transactional
	public Page<ProductDTO> getAllProductsByName(String name, Pageable pageable) {

		return productRepository.findAllByProductName(name, pageable)
				.map(ProductConverter.getInstance()::convertFromEntity);
	}

	@Override
	@Transactional
	public ProductDTO addProduct(ProductDTO productDTO) {
		Product product = ProductConverter.getInstance().convertFromDto(productDTO);
		product = productRepository.save(product);
		return ProductConverter.getInstance().convertFromEntity(product);
	}

	@Override
	@Transactional
	public void deleteProduct(Long productId) {
		Product product = getProduct(productId);
		productRepository.delete(product);
	}

	@Override
	@Transactional
	public ProductDTO updateProductPrice(ProductDTO productDTO) {
		if (Objects.nonNull(productDTO.getId()) && Objects.nonNull(productDTO.getPrice())) {
			Product product = getProduct(productDTO.getId());
			saveProductPriceHistory(product, productDTO);
			product.setPrice(productDTO.getPrice());
			product = productRepository.save(product);
			return ProductConverter.getInstance().convertFromEntity(product);
		}
		throw new BadRequestException("Product ID must be not emty!");
	}

	private void saveProductPriceHistory(Product product, ProductDTO productDTO) {
		ProductHistory productHistory = new ProductHistory();
		productHistory.setNewPrice(productDTO.getPrice());
		productHistory.setOldPrice(product.getPrice());
		productHistory.setProduct(product);
		productHistoryRepository.save(productHistory);
	}

	private Product getProduct(Long productId) {
		return productRepository.findById(productId).orElseThrow(() -> new BadRequestException("Invalid Product ID"));
	}

	@Override
	public Page<ProductDTO> searchProducts(Pageable pageable, String keyword) {
		return productRepository.findProductByKeyword(keyword, pageable).map(ProductConverter.getInstance()::convertFromEntity);
	}
}
