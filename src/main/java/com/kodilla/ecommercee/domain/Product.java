package com.kodilla.ecommercee.domain;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@AllArgsConstructor
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

    @Setter
    @ManyToOne
    @JoinColumn(name = "productItemId")
    private ProductItem productItem;

    public Product(Long id, String name, String description, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Product(Long id, String name, String description, BigDecimal price, Group group) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.group = group;
    }

    public Product(String name, String description, BigDecimal price, Group group, Order order) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.group = group;
        this.order = order;
    }

    public Product(Long Id, String name, String description, BigDecimal price, Group group, Order order) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.group = group;
        this.order = order;
    }
}