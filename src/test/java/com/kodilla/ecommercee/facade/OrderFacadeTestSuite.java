package com.kodilla.ecommercee.facade;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.service.OrderService;
import com.kodilla.ecommercee.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.time.LocalDate;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class OrderFacadeTestSuite {

    @Autowired
    private OrderFacade orderFacade;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Test
    public void testOrderConfirmation() {
        //Given
        User user = new User("User1", "1", 1234L);
        userService.save(user);
        Order order = new Order(LocalDate.now(), true, user);
        orderService.saveOrder(order);
        //When
        //orderFacade.processOrder(order);
        //Check your inbox if you have received the email
    }
}