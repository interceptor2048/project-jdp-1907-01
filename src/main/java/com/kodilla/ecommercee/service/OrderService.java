package com.kodilla.ecommercee.service;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.kodilla.ecommercee.controller.exceptions.OrderNotFoundException;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.dto.OrderDto;
import com.kodilla.ecommercee.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.StyleConstants;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;


@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

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


}
