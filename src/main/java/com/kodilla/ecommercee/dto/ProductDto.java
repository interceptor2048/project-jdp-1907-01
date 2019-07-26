package com.kodilla.ecommercee.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDto {
    private  int id;
    private String name;
    private String description;
    private double price;
    private int groupId;

    public ProductDto() {
    }

    public ProductDto(int id, String name, String description, double price, int groupId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.groupId = groupId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getGroupId() {
        return groupId;
    }
}
