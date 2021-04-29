package com.nab.product.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nab.product.dto.ProductDTO;
import com.nab.product.service.ProductService;
import com.nab.product.utils.PaginationUtil;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
	
	private ProductService productService;
	
	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping("/get-all")
	public ResponseEntity<List<ProductDTO>> getAllInfo(Pageable pageable){
        LOGGER.debug("REST request to get all products ");
		Page<ProductDTO> products = productService.getAllProduct(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), products);
        return ResponseEntity.ok().headers(headers).body(products.getContent());
	}
	
	@GetMapping("/get")
	public ResponseEntity<ProductDTO> getProduct(@RequestParam(value ="product-id") Long productId){
        LOGGER.debug("REST request to get product : {}" , productId);
        ProductDTO productDTO = productService.getProductById(productId);
		return ResponseEntity.ok(productDTO);
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<ProductDTO>> search(@RequestParam(value ="keyword") String keyword , Pageable pageable){
        LOGGER.debug("REST request to search all products with keyword : {}" , keyword);
		Page<ProductDTO> products = productService.searchProducts(pageable , keyword);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), products);
        return ResponseEntity.ok().headers(headers).body(products.getContent());
	}
}
