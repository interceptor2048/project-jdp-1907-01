package com.kodilla.ecommercee.mapper;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.dto.OrderDto;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class OrderMapper {

    public Order mapToOrder(final OrderDto orderDto) {
        //return new Order(orderDto.getId(), orderDto.getDate(), orderDto.isCompleted(), orderDto.getProductList(), orderDto.getUserId());
        return null;
    }

    public OrderDto mapToOrderDto(final Order order) {
        //return new OrderDto(order.getId(), order.getDate(), order.isCompleted(), order.getProductList(), order.getUserId());
        return null;
    }
    
    public List<OrderDto> mapToOrderDtoList(final List<Order> orderList) {
        /*return orderList.steam()
            .map(o -> new OrderDto(o.getId(), o.getDate(), o.isCompeleted(), o.getProductList(), o.getUserId()))
            .collect(Collectors.toList());
        */
        return null;
    }
}
