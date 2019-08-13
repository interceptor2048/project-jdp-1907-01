package com.kodilla.ecommercee.controller;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.kodilla.ecommercee.controller.exceptions.OrderNotFoundException;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.dto.OrderDto;
import com.kodilla.ecommercee.service.Html2PdfService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

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


    @GetMapping("getOrders")
    public List<OrderDto> getOrders() {
        return orderMapper.mapToOrderDtoList(orderService.getOrders());
    }

    @GetMapping("getOrder")
    public OrderDto getOrder(@RequestParam long id) throws OrderNotFoundException {
        return orderMapper.mapToOrderDto(orderService.getOrder(id));
    }

    @PostMapping("createOrder")
    public void createOrder(@RequestBody OrderDto orderDto) {
        orderService.saveOrder(orderMapper.mapToOrder(orderDto));
    }

    @PutMapping("editOrder")
    public OrderDto editOrder(@RequestBody OrderDto orderDto) {
        return orderMapper.mapToOrderDto(orderService.updateOrder(orderMapper.mapToOrder(orderDto)));
    }

    @DeleteMapping("deleteOrder")
    public void deleteOrder(@RequestParam long id) {
        orderService.deleteOrder(id);
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
