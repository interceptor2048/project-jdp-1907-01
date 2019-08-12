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
import javax.persistence.NamedQuery;

@NamedQuery(
        name = "User.returnUserById",
        query = "from User where id = :param"
)
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

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "cartId")
    private Cart cart;

    public User(String username, String status, Long userKey) {
        this.username = username;
        this.status = status;
        this.userKey = userKey;
    }
  
    public User(String username, String status, Long userKey, Cart cart) {
        this.username = username;
        this.status = status;
        this.userKey = userKey;
        this.cart = cart;
    }
}
