package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
    private String address;
    private String email;
    private String phoneNumber;


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "cartId")
    private Cart cart;

    public User(String username, String status, Long userKey, String phoneNumber) {
        this.username = username;
        this.status = status;
        this.userKey = userKey;
        this.phoneNumber = phoneNumber;
    }

    public User(String username, String status, Long userKey, String phoneNumber,Cart cart) {
        this.username = username;
        this.status = status;
        this.userKey = userKey;
        this.phoneNumber = phoneNumber;
        this.cart = cart;
    }
    public User(Long id, String username, String status, Long userKey, Cart cart) {
        this.username = username;
        this.status = status;
        this.userKey = userKey;
        this.cart = cart;
    }

    public User(String username, String status, Long userKey, String address, String email) {
        this.username = username;
        this.status = status;
        this.userKey = userKey;
        this.address = address;
        this.email = email;
    }

    public User(String username, String status, Long userKey, String address, String email, Cart cart) {
        this.username = username;
        this.status = status;
        this.userKey = userKey;
        this.address = address;
        this.email = email;
        this.cart = cart;
    }

    public User(String username, String status, Long userKey, String address, String email, String phoneNumber, Cart cart) {
        this.username = username;
        this.status = status;
        this.userKey = userKey;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.cart = cart;
    }
}
