package com.nab.product.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nab.product.dto.ProductDTO;

@Service
public interface ProductService {
    public Page<ProductDTO> getAllProduct(final Pageable pageable);
    
    public Page<ProductDTO> getAllProductByCategory(final String category ,final Pageable pageable);
    
    public ProductDTO getProductById(final Long id );
    
    public Page<ProductDTO> getAllProductsByName(final String name,final Pageable pageable);
    
    public ProductDTO addProduct(final ProductDTO productDTO);
    
    public void deleteProduct(final Long productId);
    
    public ProductDTO updateProductPrice(final ProductDTO productDTO);

	public Page<ProductDTO> searchProducts(Pageable pageable, String keyword);
}
