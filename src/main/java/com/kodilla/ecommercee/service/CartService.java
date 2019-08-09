package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.controller.exceptions.CartNotFoundException;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.ProductItem;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.dto.CartDto;
import com.kodilla.ecommercee.domain.dto.OrderDto;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.ProductItemRepository;
import com.kodilla.ecommercee.repository.UserRepository;
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
    private UserRepository userRepository;

    @Autowired
    private ProductItemRepository productItemRepository;


    public Cart createCart(@RequestParam long userId) {
        Optional<User> user = userRepository.findById(userId);
        Cart cart = new Cart(new ArrayList<>(), user.get());
        Cart savedCart = cartRepository.save(cart);
        return savedCart;
    }

    public Optional<Cart> getCart(final long id) { //ProductItem zamiast Cart
        return cartRepository.findById(id);
    }

    public Cart addItemToCard(@RequestParam long cartId, @RequestBody ProductItem productItem) throws CartNotFoundException {
        // na podstawie CartId wyciagnac z Repozytorium koszyk
        Cart cart = cartRepository.findById(cartId).orElseThrow(CartNotFoundException::new);
        // dla wyciagnietego koszyka dodac productItem do listy ProductsItems
        cart.getProductItems().add(productItem);

        cartRepository.save(cart);
        return cart;
    }

    public Cart removeItemFromCard(@RequestParam long cartId, @RequestParam long itemId) throws CartNotFoundException{
        Cart cart = cartRepository.findById(cartId).orElseThrow(CartNotFoundException::new);   // CartOptional = cartNew

            Optional<ProductItem> productItemOptional = productItemRepository.findById(itemId);
            if (productItemOptional.isPresent()) {
                ProductItem productItem = productItemOptional.get();
                cart.getProductItems().remove(productItem);
            }

            cartRepository.save(cart);
            return cart;
    }
}
