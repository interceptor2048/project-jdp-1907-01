package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity

@Table(name = "PRODUCT_GROUPS")
public class Group {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    public Group(String name) {
        this.name = name;
    }
}
