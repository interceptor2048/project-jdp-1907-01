package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.controller.exceptions.OrderNotFoundException;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
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

    @Test
    public void testSaveAndGetOrder() throws OrderNotFoundException {
        //Given
        Order order = new Order(13L,LocalDate.now(), true, new User());
        //When
        orderService.saveOrder(order);
        Order tempOrder = orderService.getOrder(order.getId());
        List<Order> tempOrders = orderService.getOrders();
        //Then
        assertNotNull(tempOrder);
        assertNotNull(tempOrders);
        assertEquals(LocalDate.now(), tempOrder.getDate());
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