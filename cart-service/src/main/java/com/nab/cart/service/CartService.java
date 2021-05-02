package com.nab.cart.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nab.cart.dto.CartDTO;
/**
 * Customer can have multiple carts with diff cart name.
 *
 */
@Service
public interface CartService {

	public CartDTO addToCart(CartDTO cartDTO);
	
	public CartDTO updateToCart(CartDTO cartDTO);
	
	public CartDTO updateOrderToCart(CartDTO cartDTO);
	
	public Page<CartDTO> getAllCart(String userEmail , Pageable pageable);
}
