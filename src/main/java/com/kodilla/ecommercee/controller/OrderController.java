package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.client.TrelloClient;
import com.kodilla.ecommercee.controller.exceptions.OrderNotFoundException;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.dto.OrderDto;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.service.Html2PdfService;
import com.kodilla.ecommercee.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/v1/ecommercee/order/")
@CrossOrigin("*")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Log()
public class OrderController {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ServletContext context;
    @Autowired
    ResourceLoader resourceLoader;

    private final Html2PdfService documentGeneratorService;


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
        order.setTrelloCardId(trelloClient.addOrderToList(order.getId(),TrelloClient.NEW_ORDER_LIST).getListId());
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

    @GetMapping("{id}/confirmationPdf")
    public ModelAndView createPdf(HttpServletRequest request, HttpServletResponse response, @PathVariable Long id, Model model) throws OrderNotFoundException {
       // OrderDto orderDto = orderMapper.mapToOrderDto(orderService.getOrder(id));
       // boolean isFlag = orderService.createPdf(orderDto, context, request, response);
        ModelAndView modelAndView = new ModelAndView();
        OrderDto orderDto = orderMapper.mapToOrderDto(orderService.getOrder(id));
        model.addAttribute("orderDto", orderDto);

        modelAndView.setViewName("confirmation");
        return modelAndView;
    }
    @RequestMapping(value = "{id}/confirmationPdf/download", method = RequestMethod.GET, produces = "application/pdf")
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


}
