package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Cart {

    private Long id;
    private Long userId;
    private List<ProductDto> productList;
}

