package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "\"USERS\"")
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String status;
    private Long userKey;

    @OneToOne
    @JoinColumn(name = "cartId")
    private Cart cart;

    public User(String username, String status, Long userKey, Cart cart) {
        this.username = username;
        this.status = status;
        this.userKey = userKey;
        this.cart = cart;
    }
}
