package com.kodilla.ecommercee.controller;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.ProductItem;
import com.kodilla.ecommercee.domain.dto.CartDto;
import com.kodilla.ecommercee.domain.dto.OrderDto;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/ecommercee/cart/")
@CrossOrigin("*")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private CartMapper cartMapper;

    @PostMapping(value = "createCart")
    public  CartDto createCart(@RequestParam long userId) {
        //return new CartDto();

        Cart cart = cartService.createCart(userId);
        CartDto cartDto = cartMapper.mapToCartDto(cart);
        return cartDto;
    }

    @GetMapping(value = "getCart")
    public CartDto getCart(@RequestParam long cartId) {
    //public CartDto getCart(@RequestParam long userId) {
        return new CartDto();
    }

    @PostMapping(value = "addItem")
    public CartDto addItemToCard(@RequestParam long cartId, @RequestBody ProductItem productItem) {
    //public CartDto addItemToCard(@RequestParam long userId, @RequestParam long itemId) {
        return new CartDto();
    }

    @DeleteMapping(value = "removeItem")
    public CartDto removeItemFromCard(@RequestParam long cartId, @RequestParam long itemId) {
        //public void removeItemFromCard(@RequestParam  long userId, @RequestParam long itemId) {
        return new CartDto();

    }

    @PostMapping(value = "createOrder")
    public OrderDto createOrder(@RequestParam long cartId) {
    //public OrderDto createOrder(@RequestParam long userId) {
       return new OrderDto();
    }
}