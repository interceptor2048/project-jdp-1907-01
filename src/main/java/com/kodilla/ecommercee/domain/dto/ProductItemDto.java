package com.kodilla.ecommercee.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProductItemDto {
    private Long id;
    private List<ProductDto> products;
    private int quantity;
    private BigDecimal ammount;
}
