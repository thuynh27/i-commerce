package com.nab.cart.converter;

import com.nab.cart.dto.CartDTO;
import com.nab.cart.model.Cart;

/**
 * Converts CartDTO to Cart Entity.
 * Converts Cart entity to CartDTO
 */
public class CartConverter extends BaseConverter<CartDTO, Cart> {

	private static CartConverter instance = new CartConverter();

	public static CartConverter getInstance() {
		return CartConverter.instance;
	}

	private CartConverter() {
		super(cartDTO -> new Cart(cartDTO.getId(), cartDTO.getProductItems(), cartDTO.getUserEmail() , cartDTO.getCartName()),
				cart -> new CartDTO(cart.getId(), cart.getProductItems(), cart.getUserEmail(),cart.getCartName()));
	}
}
