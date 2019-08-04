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
        Order order = new Order(LocalDate.now(), true, new User());
        //When
        orderService.saveOrder(order);
        Order tempOrder = orderService.getOrder(order.getId()).orElseThrow(OrderNotFoundException::new);
        List<Order> tempOrders = orderService.getOrders();
        //Then
        assertNotNull(tempOrder);
        assertNotNull(tempOrders);
        assertEquals(LocalDate.now(), tempOrder.getDate());
        assertEquals(1, tempOrders.size());
    }

    @Test
    public void testDeleteUser() {
        //Given
        Order order = new Order(LocalDate.now(), true, new User());
        //When
        orderService.saveOrder(order);
        orderService.deleteOrder(order.getId());
        //Then
        assertFalse(orderService.getOrder(order.getId()).isPresent());
    }
}