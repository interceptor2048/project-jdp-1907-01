package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.controller.exceptions.CartNotFoundException;
import com.kodilla.ecommercee.controller.exceptions.OrderNotFoundException;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.ProductItem;
import com.kodilla.ecommercee.domain.dto.CartDto;
import com.kodilla.ecommercee.domain.dto.OrderDto;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.service.CartService;
import com.kodilla.ecommercee.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/ecommercee/cart/")
@CrossOrigin("*")
public class CartController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private CartMapper cartMapper;

    @PostMapping(value = "createCart", consumes = MediaType.APPLICATION_JSON_VALUE)
    public  CartDto createCart(@RequestParam long userId) {
        Cart cart = cartService.createCart(userId);
        CartDto cartDto = cartMapper.mapToCartDto(cart);
        return cartDto;
    }

    @PostMapping(value = "saveCart", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveCart(@RequestBody CartDto cartDto) {
        cartService.save(cartMapper.mapToCart(cartDto));
    }

    @GetMapping(value = "getCart")
    public CartDto getCart(@RequestParam long cartId) throws CartNotFoundException {
        return cartMapper.mapToCartDto(cartService.getCart(cartId).orElseThrow(CartNotFoundException::new));
    }

    @PostMapping(value = "addItem")
    public CartDto addItemToCart(@RequestParam long cartId, @RequestBody ProductItem productItem) throws CartNotFoundException{ // @RequestParam long productId
        return cartMapper.mapToCartDto(cartService.addItemToCard(cartId, productItem));
    }

    @DeleteMapping(value = "removeItem")
    public CartDto removeItemFromCard(@RequestParam long cartId, @RequestParam long itemId) throws CartNotFoundException{
        return cartMapper.mapToCartDto(cartService.removeItemFromCard(cartId, itemId));
    }
}