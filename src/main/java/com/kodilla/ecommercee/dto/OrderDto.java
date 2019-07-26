package com.kodilla.ecommercee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Getter
public class OrderDto {

    private long id;
    private LocalDate date;
    private List<ProductDto> productList;
    private Long userId;


}
