package com.kodilla.ecommercee.dto;


import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.HashMap;

@AllArgsConstructor
@Getter
public class OrderDto {

    private Long id;
    private LocalDate date;
    private HashMap<Product, Long> productList;
    private User user;


}
