package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.CartDto;
import com.kodilla.ecommercee.dto.OrderDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("v1/ecommercee/")
public class CartController {

    @PostMapping(value = "{userId}/cart")
    public  void createCart(@PathVariable Long userId) {
    }

    @GetMapping(value = "{userId}/cart")
    public CartDto getCart(@PathVariable Long userId) {
        return new CartDto(1L,userId,new ArrayList<>());
    }

    @PostMapping(value = "{userId}/cart/{itemId}")
    public  void addItemToCard(@PathVariable Long userId, @PathVariable Long itemId,  @RequestParam Long itemQuantity) {
    }

    @PutMapping(value = "{userId}/cart/{itemId}")
    public CartDto updateCartItem(@PathVariable Long userId, @PathVariable Long itemId, @RequestParam Long itemQuantity) {
        return new CartDto(1L,userId,new ArrayList<>());
    }

    @DeleteMapping(value = "{userId}/cart/{itemId}")
    public void removeItemFromCard(@PathVariable Long userId,@PathVariable  @RequestParam Long itemId) {
    }

    @PostMapping(value = "{userId}/order/{cartId}")
    public OrderDto createOrder(@PathVariable Long userId, @PathVariable  @RequestParam Long cartId) {
       return new OrderDto();
    }

}