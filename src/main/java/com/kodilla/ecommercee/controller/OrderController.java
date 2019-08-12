package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.client.TrelloClient;
import com.kodilla.ecommercee.controller.exceptions.OrderNotFoundException;
import com.kodilla.ecommercee.controller.exceptions.UserNotFoundException;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.dto.OrderDto;
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


    @GetMapping("getOrders")
    public List<OrderDto> getOrders() {
        return orderMapper.mapToOrderDtoList(orderService.getOrders());
    }

    @GetMapping("getOrder")
    public OrderDto getOrder(@RequestParam long id) throws OrderNotFoundException {
        return orderMapper.mapToOrderDto(orderService.getOrder(id));
    }

    @PostMapping("createOrder")

    public void createOrder(@RequestBody OrderDto orderDto) throws OrderNotFoundException,UserNotFoundException{
        Order order = orderService.saveOrder(orderMapper.mapToOrder(orderDto));
        order.setTrelloCardId(trelloClient.addOrderToList(order.getId(),TrelloClient.NEW_ORDER_LIST).getListId());
    }

    @PutMapping("editOrder")
    public OrderDto editOrder(@RequestBody OrderDto orderDto) throws OrderNotFoundException,UserNotFoundException{
        Order order = orderService.updateOrder(orderMapper.mapToOrder(orderDto));
        trelloClient.updateOrder(order.getId());
        return orderMapper.mapToOrderDto(orderService.updateOrder(orderMapper.mapToOrder(orderDto)));

    }

    @DeleteMapping("deleteOrder")
    public void deleteOrder(@RequestParam long id) throws OrderNotFoundException{
        orderService.deleteOrder(id);
        trelloClient.deleteOrder(id);
    }


    @PostMapping(value = "orderProcessor")
    public Order orderProcessor(long userId) throws UserNotFoundException,OrderNotFoundException {
        User user = userService.returnUserById(userId).orElseThrow(UserNotFoundException::new);
        LOGGER.info("We proceesing order for:" + user.getUsername() + " with userKey:" + user.getUserKey());
        Cart userCart = user.getCart();
        LOGGER.info(user.getUsername() + " have "+ userCart.getProducts().size() + " product in cart");
        List<Product> productFromCart = new ArrayList<>();
        BigDecimal orderCost = BigDecimal.ZERO;
        LOGGER.info("Cart items: ");
        for(Product product : userCart.getProducts()) {
            LOGGER.info("Item:" + product.getName() + ", " + product.getPrice());
            productFromCart.add(product);
            orderCost.add(product.getPrice());
        }
        LOGGER.info("Cost of order:" + orderCost + " $");
        Order resultOrder = new Order(LocalDate.now(),true,user,orderCost);
        resultOrder.getProductList().addAll(productFromCart);
        orderService.saveOrder(resultOrder);
        resultOrder.setTrelloCardId(trelloClient.addOrderToList(resultOrder.getId(),TrelloClient.NEW_ORDER_LIST).getListId());
        orderService.saveOrder(resultOrder);
        LOGGER.info("Amound to pay: " + orderCost.toString());
        return resultOrder;
    }
}
