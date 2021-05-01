package com.nab.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nab.order.dto.OrderDTO;
import com.nab.order.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	private OrderService orderService;

	@Autowired
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@PostMapping
	public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
		return ResponseEntity.ok(orderService.addOrder(orderDTO));
	}
}
