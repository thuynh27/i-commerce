package com.nab.cart.service.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nab.cart.converter.CartConverter;
import com.nab.cart.dto.CartDTO;
import com.nab.cart.exception.BadRequestException;
import com.nab.cart.model.Cart;
import com.nab.cart.model.Product;
import com.nab.cart.repository.CartRepository;
import com.nab.cart.service.CartService;

@Service
public class CartServiceImpl implements CartService {

	private CartRepository cartRepository;

	@Autowired
	public CartServiceImpl(CartRepository cartRepository) {
		this.cartRepository = cartRepository;
	}

	@Override
	public CartDTO addToCart(CartDTO cartDTO) {
		Cart cart = CartConverter.getInstance().convertFromDto(cartDTO);
		return CartConverter.getInstance().convertFromEntity(cartRepository.save(cart));
	}

	@Override
	public CartDTO updateToCart(CartDTO cartDTO) {
		Optional<Cart> oldCart = cartRepository.findById(cartDTO.getId());
		if(oldCart.isPresent()) {
			Cart cart = oldCart.get();
			if(cart.getUserEmail().equals(cartDTO.getUserEmail())) {
				cart.setCartName(cartDTO.getCartName());
				cart.setProductItems(cartDTO.getProductItems());
				return CartConverter.getInstance().convertFromEntity(cartRepository.save(cart));
			}
		}
		throw new BadRequestException("Cart Not Exsit!");
	}

	@Override
	public Page<CartDTO> getAllCart(String userEmail , Pageable pageable) {
		return cartRepository.findByUserEmail(userEmail, pageable)
				.map(CartConverter.getInstance()::convertFromEntity);
	}

	/**
	 * Remove all exist product items when customer proceed to order process .
	 * 
	 */
	@Override
	public CartDTO updateOrderToCart(CartDTO cartDTO) {
		Optional<Cart> oldCart = cartRepository.findById(cartDTO.getId());
		if(oldCart.isPresent()) {
			Cart cart = oldCart.get();
			if(cart.getUserEmail().equals(cartDTO.getUserEmail())) {
				removeProductItems(cartDTO , cart);
				return CartConverter.getInstance().convertFromEntity(cartRepository.save(cart));
			}
		}
		throw new BadRequestException("Cart Not Exsit!");
	}

	private void removeProductItems(CartDTO cartDTO, Cart cart) {
		 cart.getProductItems().removeAll(cartDTO.getProductItems());
	}
}
