package com.kodilla.ecommercee.domain;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "\"ORDERS\"")
public class Order {

    public static String AWAITING = "is awaiting acceptance for implementation";
    public static String IN_PROGRESS = "in progress";
    public static String SEND = "send";

    @Id
    @GeneratedValue
    private Long id;
    private LocalDate date;
    private boolean isCompleted;

    @ManyToMany(mappedBy = "orders")
    private Set<ProductItem> productItems;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @Setter
    private User user;

    private BigDecimal valueOfOrder;

    @Setter
    private String trelloCardId;

    @Setter
    private String status = AWAITING;

    public Order(Long id, LocalDate date, boolean isCompleted, User user) {
        this.id = id;
        this.date = date;
        this.isCompleted = isCompleted;
        this.productItems = new HashSet<>();
        this.user = user;
    }

    public Order(LocalDate date, boolean isCompleted, User user) {
        this.date = date;
        this.isCompleted = isCompleted;
        this.productItems = new HashSet<>();
        this.user = user;
    }

    public Order(LocalDate date, boolean isCompleted) {
        this.date = date;
        this.isCompleted = isCompleted;
        this.productItems = new HashSet<>();
    }

    public Order(LocalDate date, boolean isCompleted, User user, BigDecimal valueOfOrder) {
        this.date = date;
        this.isCompleted = isCompleted;
        this.user = user;
        this.valueOfOrder = valueOfOrder;
        this.productItems = new HashSet<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;

        Order order = (Order) o;

        if (isCompleted != order.isCompleted) return false;
        if (!id.equals(order.id)) return false;
        if (!date.equals(order.date)) return false;
        if (!productItems.equals(order.productItems)) return false;
        if (!user.equals(order.user)) return false;
        if (!valueOfOrder.equals(order.valueOfOrder)) return false;
        if (!trelloCardId.equals(order.trelloCardId)) return false;
        return status.equals(order.status);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + date.hashCode();
        result = 31 * result + (isCompleted ? 1 : 0);
        result = 31 * result + productItems.hashCode();
        result = 31 * result + user.hashCode();
        result = 31 * result + valueOfOrder.hashCode();
        result = 31 * result + trelloCardId.hashCode();
        result = 31 * result + status.hashCode();
        return result;
    }
}