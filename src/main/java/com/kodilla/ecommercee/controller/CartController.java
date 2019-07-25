package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.CartDto;
import com.kodilla.ecommercee.dto.OrderDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("v1/ecommercee/cart/")
public class CartController {

    @PostMapping(value = "createCart")
    public  CartDto createCart(@RequestParam long userId) {
        return new CartDto();
    }

    @GetMapping(value = "getCart")
    public CartDto getCart(@RequestParam long userId) {
        return new CartDto(new ArrayList<>());
    }

    @PostMapping(value = "addItem")
    public  CartDto addItemToCard(@RequestParam long userId, @RequestParam long itemId) {
        return new CartDto();
    }

    @DeleteMapping(value = "removeItem")
    public void removeItemFromCard(@RequestParam  long userId, @RequestParam long itemId) {
    }

    @PostMapping(value = "createOrder")
    public OrderDto createOrder(@RequestParam  long userId) {
       return new OrderDto();
    }

}