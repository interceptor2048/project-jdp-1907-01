package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Order {

    @Id
    @GeneratedValue
    private Long id;
    private LocalDate date;
    private boolean isCompleted;
    //@OneToMany(targetEntity = Product.class, mappedBy="order", fetch=FetchType.LAZY)
    //private List<Product> productList;
    private Long userId;

}
