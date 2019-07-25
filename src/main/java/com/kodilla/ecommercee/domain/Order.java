package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.LinkedHashMap;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "time")
    private LocalDate date;

    private LinkedHashMap<Product, Long> productMap;

    @Column(name = "userId")
    private Long userId;
}
