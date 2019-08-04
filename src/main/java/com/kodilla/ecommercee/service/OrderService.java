package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.controller.exceptions.OrderNotFoundException;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }


    public Order getOrder(long id) throws OrderNotFoundException {
        List<Order> orders = orderRepository.findAll();
        for(Order order : orders) {
            if(order.getId() == id) {
                Order resultOrder = order;
                return resultOrder;
            } else {
                throw  new OrderNotFoundException();
            }
        }
        return null;

    }

    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    public Order updateOrder(Order order) {
        return orderRepository.save(order);
    }

    public void deleteOrder(long id) {
        orderRepository.deleteById(id);
    }

}
