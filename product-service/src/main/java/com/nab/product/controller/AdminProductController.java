package com.nab.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nab.product.dto.ProductDTO;
import com.nab.product.service.ProductService;


@RestController
@RequestMapping("/admin")
public class AdminProductController {
	
	private ProductService productService;
	
	@Autowired
	public AdminProductController(ProductService productService) {
		this.productService = productService;
	}

	@PostMapping("/add-product")
	public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO productDTO) {
		 return ResponseEntity.ok(productService.addProduct(productDTO));
	}

	@PutMapping("/edit-product")
	public ResponseEntity<ProductDTO> editProduct(@RequestBody ProductDTO productDTO) {
		 return ResponseEntity.ok(productService.updateProductPrice(productDTO));
	}
}
