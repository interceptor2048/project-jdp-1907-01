package com.kodilla.ecommercee.dto;


import com.kodilla.ecommercee.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OrderDto {

    private Long id;
    private LocalDate date;
    private boolean isCompleted;
    private List<Product> productList;
    private Long userId;


}
