package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.client.TrelloClient;
import com.kodilla.ecommercee.controller.exceptions.CartNotFoundException;
import com.kodilla.ecommercee.controller.exceptions.OrderNotFoundException;
import com.kodilla.ecommercee.controller.exceptions.UserNotFoundException;
import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.domain.dto.OrderDto;
import com.kodilla.ecommercee.service.CartService;
import com.kodilla.ecommercee.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/ecommercee/order/")
@CrossOrigin("*")
public class OrderController {

    public static Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    OrderService orderService;

    @Autowired
    UserService userService;

    @Autowired
    TrelloClient trelloClient;

    @Autowired
    CartService cartService;


    @GetMapping("getOrders")
    public List<OrderDto> getOrders() {
        return orderMapper.mapToOrderDtoList(orderService.getOrders());
    }

    @GetMapping("getOrder")
    public OrderDto getOrder(@RequestParam long id) throws OrderNotFoundException {
        return orderMapper.mapToOrderDto(orderService.getOrder(id));
    }

    @PutMapping("editOrder")
    public OrderDto editOrder(@RequestBody OrderDto orderDto) throws OrderNotFoundException, UserNotFoundException {
        Order order = orderService.updateOrder(orderMapper.mapToOrder(orderDto));
        trelloClient.updateOrder(order.getId());
        return orderMapper.mapToOrderDto(orderService.updateOrder(orderMapper.mapToOrder(orderDto)));

    }

    @DeleteMapping("deleteOrder")
    public void deleteOrder(@RequestParam long id) throws OrderNotFoundException {
        orderService.deleteOrder(id);
        trelloClient.deleteOrder(id);
    }

    @PostMapping(value = "orderProcessor")
    public OrderDto orderProcessor(@RequestParam("cartId") long cartId) throws CartNotFoundException, OrderNotFoundException {
        LOGGER.info("Test");
        Cart userCart = cartService.getCart(cartId).orElseThrow(CartNotFoundException::new);
        User user = userCart.getUser();
        LOGGER.info("We proceesing order for:" + user.getUsername() + " with userKey:" + user.getUserKey());
        int productCount = 0;
        BigDecimal priceOfProducts = new BigDecimal(0);
        List<ProductItem> productsItems = new ArrayList<>();
        for (ProductItem productItem : userCart.getProductItems()) {
            productCount += productItem.getQuantity();
            priceOfProducts.add(productItem.getAmount());
            productsItems.add(productItem);
        }
        LOGGER.info(user.getUsername() + " have " + productCount + "product in cart");
        Order resultOrder = new Order(LocalDate.now(), true, user, priceOfProducts);
        resultOrder.getProductItems().addAll(productsItems);
        resultOrder.setTrelloCardId(trelloClient.addOrderToList(resultOrder.getId(),TrelloClient.NEW_ORDER_LIST).getListId());
        orderService.saveOrder(resultOrder);
        LOGGER.info("Amound to pay: " + priceOfProducts.toString());
        userCart.getProductItems().clear();
        return orderMapper.mapToOrderDto(resultOrder);
    }
}
