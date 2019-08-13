package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Order;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import java.util.Map;

public interface Html2PdfService {

    InputStreamResource html2PdfGenerator(Order order);

}