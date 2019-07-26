package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.OrderDto;
import com.kodilla.ecommercee.dto.ProductDto;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/v1/ecommercee/order/")
public class OrderController {

    @GetMapping("getOrders")
    public List<OrderDto> getOrders() {
        return new ArrayList<>();
    }

    @GetMapping("getOrder")
    public OrderDto getOrder(@RequestParam long id) {
        return new OrderDto(id, LocalDate.now(), new LinkedList<ProductDto>(), 1L);
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
    public void deleteOrder(@RequestParam long id) {
        System.out.println("Order nr. " + id + " has been deleted.");
    }
}
