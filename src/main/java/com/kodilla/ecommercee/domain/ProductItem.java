package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.domain.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "\"PRODUCTS_ITEM\"")
public class ProductItem {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "product_Item_id_and_product_id",
            joinColumns = @JoinColumn(name = "product_Item_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "product_id",referencedColumnName = "id")
    )
    private List<Product> products;
    private int quantity;
    private BigDecimal ammount;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Cart> carts;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "product_items_id_and_orders_id",
            joinColumns = @JoinColumn(name = "product_item_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "order_id",referencedColumnName = "id")
    )
    private List<Order> orders;

    public ProductItem(Long id, List<Product> products, int quantity, BigDecimal ammount) {
        this.id = id;
        this.products = products;
        this.quantity = quantity;
        this.ammount = ammount;
    }
}
