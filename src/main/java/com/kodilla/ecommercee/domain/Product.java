package com.kodilla.ecommercee.domain;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "\"PRODUCTS\"")
public class Product {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    @Setter
    @ManyToOne
    @JoinColumn(name = "groupId")
    private Group group;
    @Setter
    @ManyToOne
    @JoinColumn(name = "orderId")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "cartId")
    private Cart cart;

    public Product(Long id, String name, String description, BigDecimal price, Group group, Cart cart) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.group = group;
        this.cart = cart;
    }

    public Product(String name, String description, BigDecimal price, Group group, Order order, Cart cart) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.group = group;
        this.order = order;
        this.cart = cart;
    }

    public Product(Long id, String name, String description, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
