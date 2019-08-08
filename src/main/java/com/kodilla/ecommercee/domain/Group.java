package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "\"GROUPS\"")
public class Group {

    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String name;

    @OneToMany(targetEntity = Product.class,
            mappedBy = "group",
            fetch = FetchType.EAGER)
    private List<Product> products = new ArrayList<>();

    public Group(Long id, String name) {
        this.id = id;

        this.name = name;
    }

    public Group(String name) {
        this.name = name;
    }
}

