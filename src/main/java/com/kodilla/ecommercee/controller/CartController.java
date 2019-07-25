package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.CartDto;
import com.kodilla.ecommercee.dto.OrderDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("v1/ecommercee/cart")
public class CartController {

    @PostMapping(value = "createCart")
    public  void createCart(@RequestParam long userId) {
    }

    @GetMapping(value = "getCart")
    public CartDto getCart(@RequestParam long userId) {
        return new CartDto(new ArrayList<>());
    }

    @PostMapping(value = "addItem")
    public  void addItemToCard(@PathVariable long userId, @PathVariable long itemId) {
    }

    @DeleteMapping(value = "removeItem")
    public void removeItemFromCard(@RequestParam  long userId, @RequestParam long itemId) {
    }

    @PostMapping(value = "createOrder")
    public OrderDto createOrder(@RequestParam  long userId) {
       return new OrderDto();
    }

}