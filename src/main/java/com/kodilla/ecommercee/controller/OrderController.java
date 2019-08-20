package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.client.TrelloClient;
import com.kodilla.ecommercee.controller.exceptions.CartNotFoundException;
import com.kodilla.ecommercee.controller.exceptions.OrderNotFoundException;
import com.kodilla.ecommercee.controller.exceptions.UserNotFoundException;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.ProductItem;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.dto.OrderDto;
import com.kodilla.ecommercee.facade.OrderFacade;
import com.kodilla.ecommercee.service.SimpleEmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.service.CartService;
import com.kodilla.ecommercee.service.Html2PdfService;
import com.kodilla.ecommercee.service.OrderService;
import com.kodilla.ecommercee.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/ecommercee/order/")
@CrossOrigin("*")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Log()
public class OrderController {


    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    OrderMapper orderMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderService orderService;
    @Autowired
    ResourceLoader resourceLoader;
    @Autowired
    private final Html2PdfService documentGeneratorService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TrelloClient trelloClient;
    @Autowired
    private CartService cartService;


    @Autowired
    OrderFacade orderFacade;

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


    @GetMapping("{id}/confirmation")
    public ModelAndView createPdf(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id, Model model) throws OrderNotFoundException {
        ModelAndView modelAndView = new ModelAndView();
        OrderDto orderDto = orderMapper.mapToOrderDto(orderService.getOrder(id));
        Optional<User> user = userService.getUser(orderDto.getUserId());
        if(user.isPresent()){
            model.addAttribute("address", user.get().getAddress());
            model.addAttribute("email", user.get().getEmail());
            model.addAttribute("username", user.get().getUsername());
        }
        model.addAttribute("date", orderDto.getDate());
        model.addAttribute("orderList", orderDto.getProductList());
        modelAndView.setViewName("confirmation");
        return modelAndView;
    }
    @RequestMapping(value = "{id}/confirmation/download", method = RequestMethod.GET, produces = "application/pdf")
    public ResponseEntity downloadFile(@PathVariable Long id) throws OrderNotFoundException {
        Order order = orderService.getOrder(id);
        InputStreamResource resource = documentGeneratorService.html2PdfGenerator(order);
        if (resource != null) {
            return ResponseEntity
                    .ok()
                    .body(resource);
        } else {
            return new ResponseEntity(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    @PostMapping(value = "orderProcessor")
    public Order orderProcessor(long cartId) throws CartNotFoundException,OrderNotFoundException {
        Cart userCart = cartService.getCart(cartId).orElseThrow(CartNotFoundException::new);
        User user = userCart.getUser();
        LOGGER.info("We proceesing order for:" + user.getUsername() + " with userKey:" + user.getUserKey());
        int productCount = 0;
        BigDecimal priceOfProducts = new BigDecimal(0);
        List<ProductItem> productsItems = new ArrayList<>();
        for (ProductItem productItem : userCart.getProductItems()) {
            productCount += productItem.getQuantity();
            priceOfProducts.add(productItem.getAmmount());
            productsItems.add(productItem);
        }
        LOGGER.info(user.getUsername() + " have " + productCount + "product in cart");
        Order resultOrder = new Order(LocalDate.now(), true, user, priceOfProducts);
        resultOrder.getProductItems().addAll(productsItems);
        resultOrder.setTrelloCardId(trelloClient.addOrderToList(resultOrder.getId(),TrelloClient.NEW_ORDER_LIST).getListId());
        orderService.saveOrder(resultOrder);
        LOGGER.info("Amound to pay: " + priceOfProducts.toString());
        userCart.getProductItems().clear();
        return resultOrder;
    }
}
