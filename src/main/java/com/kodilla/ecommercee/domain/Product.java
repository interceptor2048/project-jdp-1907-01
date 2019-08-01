package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.web.servlet.filter.OrderedFilter;

import javax.persistence.*;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "PRODUCTS")
public class Product {

    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String name;
    private String description;
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "groupId")
    private Group group;

    @ManyToOne
    @JoinColumn(name = "orderId")
    private Order order;

//    @ManyToOne
//    @JoinColumn(name = "orderId")
//    private Order order;

    public Product(Long id, String name, String description, BigDecimal price, Group group) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.group = group;
    }

    public Product(String name, String description, BigDecimal price, Group group) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.group = group;
        this.order = order;
    }
    public Product(String name, String description, BigDecimal price, Group group, Order order) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.group = group;
        this.order = order;
    }
    public Product(String name, String description, BigDecimal price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Group getGroup() {
        return group;
    }

    public Order getOrder() {
        return order;
    }
}
