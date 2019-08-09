package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.client.TrelloClient;
import com.kodilla.ecommercee.controller.exceptions.OrderNotFoundException;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.dto.OrderDto;
import org.springframework.web.bind.annotation.*;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@RestController
@RequestMapping("/v1/ecommercee/order/")
@CrossOrigin("*")
public class OrderController {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    OrderService orderService;

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
    public void createOrder(@RequestBody OrderDto orderDto) throws OrderNotFoundException{
        Order order = orderService.saveOrder(orderMapper.mapToOrder(orderDto));
        order.setTrelloCardId(trelloClient.addOrderToList(order.getId(),TrelloClient.NEW_ORDER).getListId());
    }

    @PutMapping("editOrder")
    public OrderDto editOrder(@RequestBody OrderDto orderDto) throws OrderNotFoundException{
        Order order = orderService.updateOrder(orderMapper.mapToOrder(orderDto));
        trelloClient.updateOrder(order.getId());
        return orderMapper.mapToOrderDto(orderService.updateOrder(orderMapper.mapToOrder(orderDto)));

    }

    @DeleteMapping("deleteOrder")
    public void deleteOrder(@RequestParam long id) throws OrderNotFoundException{
        orderService.deleteOrder(id);
        trelloClient.deleteOrder(id);
    }
}
