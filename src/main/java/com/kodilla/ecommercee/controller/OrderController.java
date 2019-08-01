package com.kodilla.ecommercee.controller;
import com.kodilla.ecommercee.domain.dto.OrderDto;
import com.kodilla.ecommercee.domain.dto.ProductDto;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/v1/ecommercee/order/")
@CrossOrigin("*")
public class OrderController {

    @GetMapping("getOrders")
    public List<OrderDto> getOrders() {
        return new ArrayList<>();
    }

    @GetMapping("getOrder")
    public OrderDto getOrder(@RequestParam long id) {
        return new OrderDto(id, LocalDate.now(), false, new LinkedList<ProductDto>(), 1L);
    }

    @PostMapping("createOrder")
    public void createOrder(@RequestBody OrderDto orderDto) {
        System.out.println("New order has been placed!");
    }

    @PutMapping("updateOrder")
    public OrderDto updateOrder(@RequestBody OrderDto orderDto) {
        return orderDto;
    }

    @DeleteMapping("deleteOrder")
    public void deleteOrder(@RequestParam long id) {
        System.out.println("Order nr. " + id + " has been deleted.");
    }
}
