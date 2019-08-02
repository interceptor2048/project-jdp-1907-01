package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.dto.OrderDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderMapperTestSuite {

    @Autowired
    OrderMapper orderMapper;

    public Order getOrder(){
        return new Order(20L, LocalDate.now(),true,new User(1L,"Jessie","busy",1234L));
    }

    public OrderDto getOrderDto(){
        return new OrderDto(20L, LocalDate.now(),true,35L);
    }

    @Test
    public void shouldMapToOrder(){
        //Given
        OrderDto orderDto = getOrderDto();
        //When
        Order order = orderMapper.mapToOrder(orderDto);
        //Then
        assertEquals(orderDto.getDate(),order.getDate());
        assertEquals(orderDto.getId(),order.getId());
    }

    @Test
    public void shouldMapToOrderDto() {
        //Given
        Order order = getOrder();
        //When
        OrderDto orderDto = getOrderDto();
        //Then
        assertEquals(order.getId(),orderDto.getId());
        assertEquals(order.getDate(),orderDto.getDate());
    }

    @Test
    public void shouldMapToOrderDtoList() {
        //Given
        List<Order> orders = new ArrayList<>(Arrays.asList(getOrder()));
        List<OrderDto> orderDtos = orderMapper.mapToOrderDtoList(orders);
        //When
        Order order = orders.get(0);
        OrderDto orderDto = orderDtos.get(0);
        //Then
        assertEquals(order.getId(),orderDto.getId());
        assertEquals(orders.size(),orderDtos.size());
    }

}