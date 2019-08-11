package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


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

    public User(String username, String status, Long userKey,String phoneNumber, Cart cart) {

        this.username = username;
        this.status = status;
        this.userKey = userKey;
        this.phoneNumber = phoneNumber;
        this.cart = cart;
    }


}
