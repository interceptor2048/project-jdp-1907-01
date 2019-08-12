package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.domain.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "\"PRODUCTS_ITEM\"")
public class ProductItem {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "productItem")
    private List<Product> products;
    private int quantity;
    private BigDecimal ammount;

    @ManyToOne
    @JoinColumn(name = "cartId")
    private Cart cart;

    public ProductItem(Long id, List<Product> products, int quantity, BigDecimal ammount) {
        this.id = id;
        this.products = products;
        this.quantity = quantity;
        this.ammount = ammount;
    }
}
