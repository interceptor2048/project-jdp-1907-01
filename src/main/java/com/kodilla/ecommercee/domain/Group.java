package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Group {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
}
