package com.nab.cart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nab.cart.dto.CartDTO;
import com.nab.cart.service.CartService;
import com.nab.cart.utils.PaginationUtil;

@RestController
@RequestMapping("/cart")
public class CartController {

	private CartService cartService;

	@Autowired
	public CartController(CartService cartService) {
		this.cartService = cartService;
	}

	@GetMapping("/get")
	public ResponseEntity<List<CartDTO>> getAllCartCustomer(Pageable pageable ,@RequestParam(value ="user") String userEmail ) {
		Page<CartDTO> carts = cartService.getAllCart(userEmail, pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), carts);
		return ResponseEntity.ok().headers(headers).body(carts.getContent());
	}

	@PostMapping("/add")
	public ResponseEntity<CartDTO> addToCart(@RequestBody CartDTO cartDTO) {
		return ResponseEntity.ok(cartService.addToCart(cartDTO));
	}

	@PutMapping("/update")
	public ResponseEntity<CartDTO> updateCart(@RequestBody CartDTO cartDTO) {
		return ResponseEntity.ok(cartService.updateToCart(cartDTO));
	}
	
	@PutMapping("/update-order")
	public ResponseEntity<CartDTO> updateOrderCart(@RequestBody CartDTO cartDTO) {
		return ResponseEntity.ok(cartService.updateOrderToCart(cartDTO));
	}
}
