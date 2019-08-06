package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.controller.exceptions.OrderNotFoundException;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTestSuite {

    @Autowired
    private OrderService orderService;

    Logger LOGGER = LoggerFactory.getLogger(OrderServiceTestSuite.class);

    @Test
    public void testSaveAndGetOrder()  {
        //Given
        Order tempOrder = null;
        Order order = new Order(LocalDate.now(), true, new User());
        //When
        orderService.saveOrder(order);
        try {
             tempOrder = orderService.getOrder(order.getId());
        } catch (OrderNotFoundException e) {
           LOGGER.error(e.getMessage());
        }
        //Then
        assertEquals(order.getId(),tempOrder.getId());
    }

    @Test
    public void testDeleteUser()  {
        //Given
        Order order = new Order(LocalDate.now(), true, new User());
        //When
        orderService.saveOrder(order);
        List<Order> ordersAfterAdd = orderService.getOrders();
        orderService.deleteOrder(order.getId());
        List<Order> ordersAfterRemove = orderService.getOrders();
        //Then
        assertTrue(ordersAfterAdd.size() - ordersAfterRemove.size() == 1);
    }
}