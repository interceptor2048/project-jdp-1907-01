package com.kodilla.ecommercee.facade;

import com.kodilla.ecommercee.config.AdminConfig;
import com.kodilla.ecommercee.controller.exceptions.OrderNotFoundException;
import com.kodilla.ecommercee.domain.Mail;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.service.OrderService;
import com.kodilla.ecommercee.service.SimpleEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderFacade {

    private static final String SUBJECT = "Orders: New Order";

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private OrderService orderService;

    @Autowired
    private SimpleEmailService simpleEmailService;

    public void processOrder(Order order) throws OrderNotFoundException {
        Order newOrder = orderService.getOrder(order.getId());

        simpleEmailService.send(new Mail(
                adminConfig.getAdminMail(),
                adminConfig.getAdminMail(), SUBJECT, newOrder.getId().toString()));
    }
}
