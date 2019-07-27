package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "productgroups")
public class Group {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @OneToMany(mappedBy="group")
    private List<Product> products;

}
