package com.nab.order.service.feign;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.nab.order.dto.CartDTO;

import feign.FeignException;

@FeignClient(name = "cart-ws", fallbackFactory = CartServiceFallbackFactory.class)
public interface CartServiceClient {
	
	@PutMapping("/cart/update-order")
	public CartDTO updateCartDetails(@RequestBody CartDTO cartDTO) ;
}


@Component
class CartServiceFallbackFactory implements FallbackFactory<CartServiceClient> {

	@Override
	public CartServiceClient create(Throwable cause) {
		return new CartServiceServiceClientFallback(cause);
	}

}

class CartServiceServiceClientFallback implements CartServiceClient {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	private final Throwable cause;

	public CartServiceServiceClientFallback(Throwable cause) {
		this.cause = cause;
	}

	@Override
	public CartDTO updateCartDetails(CartDTO cartDTO) {
		if (cause instanceof FeignException && ((FeignException) cause).status() == 404) {
			logger.error(cause.getLocalizedMessage());
		} else {
			logger.error(cause.getLocalizedMessage());
		}

		return null;
	}

}