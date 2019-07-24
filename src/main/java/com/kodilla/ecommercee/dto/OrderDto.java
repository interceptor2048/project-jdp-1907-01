package com.kodilla.ecommercee.dto;


import com.kodilla.ecommercee.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Getter
public class OrderDto {

    private Long id;
    private LocalDate date;
    private List<Product> productList;


}
