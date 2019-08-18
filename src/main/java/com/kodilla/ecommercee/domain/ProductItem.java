package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.domain.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    private BigDecimal ammount;

    @ManyToMany(mappedBy = "productItems")
    private List<Cart> carts;

    @ManyToMany
    @JoinTable(
            name = "product_items_id_and_orders_id",
            joinColumns = @JoinColumn(name = "product_item_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "order_id",referencedColumnName = "id")
    )
    private List<Order> orders;

    public ProductItem(Long id, Product product, int quantity, BigDecimal ammount) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.ammount = ammount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductItem)) return false;

        ProductItem that = (ProductItem) o;

        if (quantity != that.quantity) return false;
        if (!id.equals(that.id)) return false;
        if (!ammount.equals(that.ammount)) return false;
        if (!carts.equals(that.carts)) return false;
        return orders.equals(that.orders);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + quantity;
        result = 31 * result + ammount.hashCode();
        result = 31 * result + carts.hashCode();
        result = 31 * result + orders.hashCode();
        return result;
    }
}
