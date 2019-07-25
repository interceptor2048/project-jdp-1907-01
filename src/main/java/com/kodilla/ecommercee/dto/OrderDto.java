package com.kodilla.ecommercee.dto;


import com.kodilla.ecommercee.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.LinkedHashMap;

@AllArgsConstructor
@Getter
public class OrderDto {

    private Long id;
    private LocalDate date;
    private LinkedHashMap<Product, Long> productMap;
    private Long userId;


}
