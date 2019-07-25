package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.dto.OrderDto;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/v1/ecommercee/order")
public class OrderController {

    @GetMapping("getOrders")
    public List<OrderDto> getOrders() {
        return new ArrayList<>();
    }

    @GetMapping("getOrder")
    public OrderDto getOrder(@RequestParam Long id) {
        return new OrderDto(id, LocalDate.now(), new HashMap<Product, Long>(), new User());
    }

    @PostMapping("createOrder")
    public void createOrder(@RequestBody OrderDto orderDto) {
        System.out.println("New order has been placed!");
    }

    @PutMapping("editOrder")
    public OrderDto editOrder(@RequestBody OrderDto orderDto) {
        return orderDto;
    }

    @DeleteMapping("deleteOrder")
    public void deleteOrder(@RequestParam Long id) {
        System.out.println("Order nr. " + id + " has been deleted.");
    }
}
