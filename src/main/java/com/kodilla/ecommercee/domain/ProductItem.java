package com.kodilla.ecommercee.domain;

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

   @ManyToOne(cascade = CascadeType.ALL)
   @JoinColumn(name = "product_id")
    private Product product;
    private int quantity;
    private BigDecimal amount;

    @ManyToMany(mappedBy = "productItems")
    private List<Cart> carts;

    @ManyToMany
    @JoinTable(
            name = "product_items_id_and_orders_id",
            joinColumns = @JoinColumn(name = "product_item_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "order_id",referencedColumnName = "id")
    )
    private List<Order> orders;

    public ProductItem(Long id, Product product, int quantity, BigDecimal amount) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductItem)) return false;

        ProductItem that = (ProductItem) o;

        if (quantity != that.quantity) return false;
        if (!id.equals(that.id)) return false;
        if (!amount.equals(that.amount)) return false;
        if (!carts.equals(that.carts)) return false;
        return orders.equals(that.orders);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + quantity;
        result = 31 * result + amount.hashCode();
        result = 31 * result + carts.hashCode();
        result = 31 * result + orders.hashCode();
        return result;
    }
}
