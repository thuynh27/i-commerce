package com.nab.order.service;

import org.springframework.stereotype.Service;

import com.nab.order.dto.OrderDTO;
import com.nab.order.dto.OrderPaymentDTO;

@Service
public interface OrderService {

	public OrderDTO addOrder(OrderDTO orderDTO);
	
	public void updateOrderStatus(OrderPaymentDTO orderDTO);
}
