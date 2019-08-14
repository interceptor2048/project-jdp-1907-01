package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "\"CARTS\"")

public class Cart {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "cart")
    private List<ProductItem> productItems;

    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private User user;

    public Cart(Long id) {
        this.id = id;
    }

    public Cart(List<ProductItem> productItems, User user) {
        this.productItems = productItems;
        this.user = user;
    }
}
