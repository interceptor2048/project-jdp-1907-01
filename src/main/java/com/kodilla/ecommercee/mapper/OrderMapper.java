package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.dto.OrderDto;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public Order mapToOrder(OrderDto orderDto) {
        return new Order(orderDto.getId(), orderDto.getDate(), orderDto.isCompleted(), orderDto.getProductList(), orderDto.getUserId());
    }

    public OrderDto mapToOrderDto(Order order) {
        return new OrderDto(order.getId(), order.getDate(), order.isCompleted(), order.getProductList(), order.getUserId());
    }

}
