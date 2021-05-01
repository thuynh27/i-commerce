package com.nab.order.service;

import org.springframework.stereotype.Service;

import com.nab.order.dto.OrderDTO;

@Service
public interface OrderService {

	public OrderDTO addOrder(OrderDTO orderDTO);
}
