package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
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

    @Setter
    @OneToMany(mappedBy = "cart")
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
