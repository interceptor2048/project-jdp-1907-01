package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Cart {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private User user;

    @OneToMany
    private List<Product> productList;
}

