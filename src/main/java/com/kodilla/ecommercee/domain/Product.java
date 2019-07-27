package com.kodilla.ecommercee.domain;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Product {

    @Id
    @GeneratedValue
    private Long id;
    private String productName;
    private BigDecimal price;
    @OneToOne
    @JoinColumn(name = "groupId")
    private Group group;
    /*@ManyToOne
    @JoinColumn(name = "orderId")
    private Order order;*/
}
