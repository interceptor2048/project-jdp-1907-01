package com.kodilla.ecommercee.mapper;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.dto.OrderDto;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    public Order mapToOrder(OrderDto orderDto) {
        return new Order(orderDto.getId(),orderDto.getDate(),orderDto.isCompleted(),orderDto.getUserId());
    }

    public List<Order> mapToOrderList(List<OrderDto> orderDtos) {
        return orderDtos.stream()
                .map(o -> new Order(o.getId(),o.getDate(),o.isCompleted(),o.getUserId()))
                .collect(Collectors.toList());
    }

    public OrderDto mapToOrderDto(Order order) {
        return new OrderDto(order.getId(),order.getDate(),order.isCompleted(),order.getUserId());
    }

    public List<OrderDto> mapToOrderDtoList(List<Order> orders) {
        return  orders.stream()
                .map(o -> new OrderDto(o.getId(),o.getDate(),o.isCompleted(),o.getUserId()))
                .collect(Collectors.toList());
    }
}
