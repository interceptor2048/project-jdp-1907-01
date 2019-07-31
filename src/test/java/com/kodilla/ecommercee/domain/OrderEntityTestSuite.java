package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.OrderRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderEntityTestSuite {

    @Autowired
    OrderRepository orderRepository;

    public static Logger LOGGER = LoggerFactory.getLogger(OrderEntityTestSuite.class);

    public Order createContent() {
        return new Order(LocalDate.now(), true);
    }

    @Test
    public void shouldAddToDB() {
        //Given
        Order order = createContent();
        orderRepository.save(order);
        //When
        long recordsCount = orderRepository.count();
        //Then
        Assert.assertEquals(1, recordsCount);
        //CleanUp
        orderRepository.deleteById(order.getId());
    }

    @Test
    public void shouldDeleteFromDB() {
        //Given
        Order order = createContent();
        //When
        orderRepository.save(order);
        LOGGER.info("After adding element count of records in table is : " + orderRepository.count());
        long recordsCountAfterAdding = orderRepository.count();
        orderRepository.deleteById(order.getId());
        LOGGER.info("After removing element count of records int table is :" + orderRepository.count());
        long reordCountAfterRemoving = orderRepository.count();
        //Then
        Assert.assertTrue(recordsCountAfterAdding - reordCountAfterRemoving == 1);
    }

    @Test
    public void shouldGetOrder() {
        //Given
        Order order = createContent();
        orderRepository.save(order);
        //Then
        Optional resultOrder = orderRepository.findById(order.getId());
        //Then
        Assert.assertTrue(Optional.ofNullable(resultOrder).isPresent());
        //Clean Up
        orderRepository.deleteById(order.getId());
    }

    @Test
    public void shouldUpdateOrder() {
        //Given
        Order order = createContent();
        orderRepository.save(order);
        List<Order> resultOrders = orderRepository.findAll();
        Order resultOrder = resultOrders.get(0);
        //When
        Order updateOrder = new Order(resultOrder.getId(),resultOrder.getDate(),false,resultOrder.getUser());
        orderRepository.save(updateOrder);
        List<Order> resultUpdateOrdersList = orderRepository.findAll();
        //Then
        Assert.assertEquals(1,resultUpdateOrdersList.size());
        Assert.assertEquals(false,updateOrder.isCompleted());
    }
}
