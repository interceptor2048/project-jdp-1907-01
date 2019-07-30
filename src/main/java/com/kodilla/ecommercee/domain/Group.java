package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Group {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

//    public Group(String name) {
//        this.name = name;
//    }

    @OneToMany(mappedBy="group")
    private List<Product> products;
}
