package com.nab.order.converter;

import com.nab.order.dto.OrderDTO;
import com.nab.order.model.Order;

/**
 * Converts OrderDTO to Order Entity. Converts Order entity to OrderDTO
 */
public class OrderConverter extends BaseConverter<OrderDTO, Order> {

	private static OrderConverter instance = new OrderConverter();

	public static OrderConverter getInstance() {
		return OrderConverter.instance;
	}

	private OrderConverter() {
		super(orderDTO -> new Order(orderDTO.getId(), orderDTO.getUserEmail(), orderDTO.getCartId(),
				ProductConverter.getInstance().createFromDtos(orderDTO.getProductItems()), orderDTO.getTotalPrice()),
				order -> new OrderDTO(order.getId(), order.getUserEmail(), order.getCartId(),
						ProductConverter.getInstance().createFromEntities(order.getProductItems()),
						order.getTotalPrice()));
	}

}
