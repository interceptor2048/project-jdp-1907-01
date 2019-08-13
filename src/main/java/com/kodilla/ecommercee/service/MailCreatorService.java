package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.config.AdminConfig;
import com.kodilla.ecommercee.controller.exceptions.OrderNotFoundException;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.List;

@Component
public class MailCreatorService {

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    @Autowired
    private OrderService orderService;

    @Autowired
    private AdminConfig adminConfig;

    String orderConfirmation(String orderId) throws OrderNotFoundException {
        Order order = orderService.getOrder(Long.parseLong(orderId));
        List<Product> products = order.getProductList();

        StringBuilder message = new StringBuilder("New Order #" + orderId + " - " + order.getDate());

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("products", products);
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("order_url", "https://dry-beyond-69165.herokuapp.com/v1/ecommercee/order/getOrder?id=" + orderId);
        context.setVariable("button", "SHOW ORDER");
        context.setVariable("company_details", "Kodilla Ecommerce");

        return templateEngine.process("mail/created-order-mail", context);
    }
}
