package com.kodilla.ecommercee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
@Entity
@Table(name = "product")
public class Product {

    private  int id;
    private String name;
    private String description;
    private double price;
    private int groupId;

    public Product() {
    }

    public Product(int id, String name, String description, double price, int groupId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.groupId = groupId;
    }

    @Id
    @NotNull
    @Column(name = "id", unique = true)
    public int getId() {
        return id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    @Column(name = "price")
    public double getPrice() {
        return price;
    }

    @Column(name = "group_Id")
    public int getGroupId() {
        return groupId;
    }

    public void setId(int id) {
        this.id = id;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    private void setPrice(double price) {
        this.price = price;
    }

    private void setGroupId(int groupId) {
        this.groupId = groupId;
    }
}
