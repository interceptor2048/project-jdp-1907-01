package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.controller.exceptions.OrderNotFoundException;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.ProductItem;
import com.kodilla.ecommercee.domain.dto.OrderDto;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    public Order updateOrder(Order order) {
        return orderRepository.save(order);
    }

    public void deleteOrder(long id) {
        orderRepository.deleteById(id);
    }

    public Order getOrder(long id) throws OrderNotFoundException{
        return orderRepository.findById(id).orElseThrow(OrderNotFoundException::new);
    }

    public Order createOrder(@RequestParam long cartId) {
        Order order = new Order();
        // na podstawie CartId wyciagnac z Repozytorium koszyk
        Optional<Cart> cartOptional = cartRepository.findById(cartId);
        if(cartOptional.isPresent()) {
            Cart cart = cartOptional.get();
            // dla wyciagnitego koszyka pobrac liste productsItems
            List<ProductItem> productItems = cart.getProductItems();
            // za pomoca listy ProductsItems uzupelnic OrderDto
            List<Product> products = new ArrayList<>();
            for (ProductItem productItem:productItems) {
                products.addAll(productItem.getProducts());
            }
            // zapisac zamowienie w batabase za pomoca orderRepository

            order.setProductList(products);
            order.setCart(cart);
            orderRepository.save(order);
        }
        return order;
    }
}
