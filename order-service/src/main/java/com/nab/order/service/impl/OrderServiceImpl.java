package com.nab.order.service.impl;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nab.order.converter.CartProductConverter;
import com.nab.order.converter.OrderConverter;
import com.nab.order.dto.CartDTO;
import com.nab.order.dto.OrderDTO;
import com.nab.order.dto.OrderPaymentDTO;
import com.nab.order.exception.BadRequestException;
import com.nab.order.model.Order;
import com.nab.order.model.ProductDetail;
import com.nab.order.repository.OrderRepository;
import com.nab.order.service.OrderService;
import com.nab.order.service.feign.CartServiceClient;

@Service
public class OrderServiceImpl implements OrderService {

	private static final String IN_PROGRESS = "IN_PROGRESS";
	private OrderRepository orderRepository;

	private CartServiceClient cartServiceClient;

	@Autowired
	public OrderServiceImpl(OrderRepository orderRepository, CartServiceClient cartServiceClient) {
		this.orderRepository = orderRepository;
		this.cartServiceClient = cartServiceClient;
	}

	@Override
	@Transactional
	public OrderDTO addOrder(OrderDTO orderDTO) {
		Order order = OrderConverter.getInstance().convertFromDto(orderDTO);
		mapProductDetails(order);
		updateCartData(orderDTO);
		order.setOrderStatus(IN_PROGRESS);
		return OrderConverter.getInstance().convertFromEntity(orderRepository.save(order));
	}

	private void updateCartData(OrderDTO orderDTO) {
		CartDTO cart = new CartDTO();
		cart.setId(orderDTO.getCartId());
		cart.setUserEmail(orderDTO.getUserEmail());
		cart.setProductItems(CartProductConverter.getInstance().createFromDtos(orderDTO.getProductItems()));
		cartServiceClient.updateCartDetails(cart);
	}

	private void mapProductDetails(Order order) {
		BigDecimal totalPrice = BigDecimal.ZERO;
		for (ProductDetail product : order.getProductItems()) {
			product.setOrder(order);
			totalPrice = totalPrice.add((product.getPrice().multiply(BigDecimal.valueOf(product.getAvailability()))));
		}
		order.setTotalPrice(totalPrice);
	}

	@Override
	public void updateOrderStatus(OrderPaymentDTO orderDTO) {
		Order order = orderRepository.findById(orderDTO.getOrderId())
				.orElseThrow(() -> new BadRequestException("Order ID not exsits!"));
		order.setOrderStatus(orderDTO.getOrderStatus());
		orderRepository.save(order);
	}
}
