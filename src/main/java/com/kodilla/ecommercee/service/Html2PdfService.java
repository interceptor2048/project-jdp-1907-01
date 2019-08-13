package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Order;
import org.springframework.core.io.InputStreamResource;


public interface Html2PdfService {

    InputStreamResource html2PdfGenerator(Order order);

}