package com.kodilla.ecommercee.controller;
import com.kodilla.ecommercee.domain.dto.CartDto;
import com.kodilla.ecommercee.domain.dto.OrderDto;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@RestController
@RequestMapping("/v1/ecommercee/cart/")
public class CartController {

    @GetMapping(value = "getCart")
    public CartDto getCart(@RequestParam long userId) {
        return new CartDto(new ArrayList<>());
    }

    @PostMapping(value = "createCart")
    public CartDto createCart(@RequestParam long userId) {
        return new CartDto();
    }

    @PostMapping(value = "addItem")
    public CartDto addItemToCart(@RequestParam long userId, @RequestParam long itemId) {
        return new CartDto();
    }

    @DeleteMapping(value = "removeItem")
    public void removeItemFromCart(@RequestParam long userId, @RequestParam long itemId) {
    }

    @PostMapping(value = "createOrder")
    public OrderDto createOrder(@RequestParam long userId) {
        return new OrderDto();
    }
}