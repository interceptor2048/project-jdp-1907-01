package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "\"ORDERS\"")
public class Order {

    @Id
    @GeneratedValue
    private Long id;
    private LocalDate date;
    private boolean isCompleted;
    @Setter
    @OneToMany(targetEntity = Product.class, mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Product> productList;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @Setter
    private User user;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "cart_id")
    @Setter
    private Cart cart;

    public Order(Long id, LocalDate date, boolean isCompleted, User user) {
        this.id = id;
        this.date = date;
        this.isCompleted = isCompleted;
        this.productList = new ArrayList<>();
        this.user = user;
    }

    public Order(LocalDate date, boolean isCompleted, User user) {
        this.date = date;
        this.isCompleted = isCompleted;
        this.productList = new ArrayList<>();
        this.user = user;
    }

    public Order(LocalDate date, boolean isCompleted) {
        this.date = date;
        this.isCompleted = isCompleted;
        this.productList = new ArrayList<>();
    }
}