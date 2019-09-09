package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.client.TrelloClient;
import com.kodilla.ecommercee.client.clientDto.TrelloOrder;
import com.kodilla.ecommercee.controller.OrderController;
import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.domain.dto.OrderDto;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.service.CartService;
import com.kodilla.ecommercee.service.OrderService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(OrderController.class)
public class OrderProcessTestSuite {

    private Cart cart;
    private Order order;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CartService cartService;

    @MockBean
    TrelloClient trelloClient;

    @MockBean
    OrderService orderService;

    @MockBean
    OrderController orderController;

    @MockBean
    OrderMapper orderMapper;


    @Before
    public void setup() {
        User user = new User("User test", "Test user", 17051L, "3125125");
        Group group = new Group(1L, "Test group");
        order = new Order(10L, LocalDate.now(), false, new HashSet<>(), user, new BigDecimal(124125.32), "1257012750fasf", Order.AWAITING);
        Product product1 = new Product(12L, "Test product1", "Test desc1", new BigDecimal(23.98), group);
        Product product2 = new Product(15L, "Test product2", "Test desc2", new BigDecimal(125.80), group);
        ProductItem productItem1 = new ProductItem(142L, product1, 3, new BigDecimal(32.5));
        ProductItem productItem2 = new ProductItem(143L, product2, 2, new BigDecimal(132.5));
        List<ProductItem> itemList = new ArrayList<>();
        itemList.add(productItem1);
        itemList.add(productItem2);
        cart = new Cart(152L, itemList, user);
    }

    @Test
    public void testCreateOrder() throws Exception {
        //When
        when(cartService.getCart(anyLong())).thenReturn(Optional.of(cart));
        when(trelloClient.addOrderToList(anyLong(), anyString())).thenReturn(new TrelloOrder("123", "Test name",
                "Test desc", "testtest", "12451251"));
        when(orderMapper.mapToOrderDto(ArgumentMatchers.any())).thenReturn(new OrderDto(10L, LocalDate.now(), false, new HashSet<>(), 23L, "Waiting"));

        //When & Then
        mockMvc.perform(post("/v1/ecommercee/order/orderProcessor?cartId=152")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(10)))
                .andExpect(jsonPath("$.date", is(LocalDate.now())))
                .andExpect(jsonPath("$.isCompleted", is(false)));
        verify(orderService).saveOrder(ArgumentMatchers.any());
        //Then
    }
}
