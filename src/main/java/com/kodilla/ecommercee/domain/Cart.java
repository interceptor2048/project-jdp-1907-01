package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.domain.dto.ProductDto;
import com.sun.javafx.beans.IDProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "\"CARTS\"")

public class Cart {
//    private List<ProductDto> productList;
//    private Long cartId;
//    private int productsQuantity;
//    private Group group;

    @Id
    @GeneratedValue
    private Long id;
    private List<ProductItem> productItems;     // ma listę elementow zamowionych przez klienta (opisana przez productItem)
    private User user;

    public Cart(List<ProductItem> productItems, User user) {
        this.productItems = productItems;
        this.user = user;
    }
}
