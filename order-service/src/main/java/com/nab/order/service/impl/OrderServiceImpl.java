package com.nab.order.service.impl;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nab.order.converter.OrderConverter;
import com.nab.order.dto.OrderDTO;
import com.nab.order.model.Order;
import com.nab.order.model.ProductDetail;
import com.nab.order.repository.OrderRepository;
import com.nab.order.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	private OrderRepository orderRepository;

	@Autowired
	public OrderServiceImpl(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	@Override
	@Transactional
	public OrderDTO addOrder(OrderDTO orderDTO) {
		Order order = OrderConverter.getInstance().convertFromDto(orderDTO);
		mapProductDetails(order);
		return  OrderConverter.getInstance().convertFromEntity(orderRepository.save(order));
	}

	private void mapProductDetails(Order order) {
		BigDecimal totalPrice = BigDecimal.ZERO;
		for(ProductDetail product : order.getProductItems()) {
			product.setOrder(order);
			totalPrice = totalPrice.add((product.getPrice().multiply(BigDecimal.valueOf(product.getAvailability()))));
		}
		order.setTotalPrice(totalPrice);
	}
}
