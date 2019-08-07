package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.ProductItem;
import com.kodilla.ecommercee.domain.dto.CartDto;
import com.kodilla.ecommercee.domain.dto.OrderDto;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.ProductItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductItemRepository productItemRepository;

    @Autowired
    private CartMapper cartMapper;

    public Cart createCart(@RequestParam long userId) {
        return new Cart();
    }

    public Optional<Cart> getCart(final long id) { //ProductItem zamiast Cart
        return cartRepository.findById(id);
    }

    public Optional<Cart> addItemToCard(@RequestParam long cartId, @RequestBody ProductItem productItem) {
        // na podstawie CartId wyciagnac z Repozytorium koszyk
        Optional<Cart> cartOptional = cartRepository.findById(cartId);
        if (cartOptional.isPresent()) {
            Cart cart = cartOptional.get();
            // dla wyciagnietego koszyka dodac productItem do listy ProductsItems
            cart.getProductItems().add(productItem);

            cartRepository.save(cart);
            return Optional.of(cart);
        }
        return Optional.empty();
    }

    public Optional<Cart> removeItemFromCard(@RequestParam long cartId, @RequestParam long itemId) {
        Optional<Cart> cartOptional = cartRepository.findById(cartId);
        if (cartOptional.isPresent()) {
            Cart cart = cartOptional.get();

            Optional<ProductItem> productItemOptional = productItemRepository.findById(itemId);
            if (productItemOptional.isPresent()) {
                ProductItem productItem = productItemOptional.get();
                cart.getProductItems().remove(productItem);
            }

            cartRepository.save(cart);
            return Optional.of(cart);
        }
        return Optional.empty();
    }
}
