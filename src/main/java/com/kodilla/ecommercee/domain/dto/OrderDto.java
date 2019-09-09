package com.kodilla.ecommercee.domain.dto;
import com.kodilla.ecommercee.domain.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OrderDto {
    private Long id;
    private LocalDate date;
    private boolean isCompleted;
    private Set<ProductItemDto> productItemDtos;
    private Long userId;
    private String status = Order.AWAITING;

    public OrderDto(Long id, LocalDate date, boolean isCompleted, Long userId) {
        this.id = id;
        this.date = date;
        this.isCompleted = isCompleted;
        this.userId = userId;
        this.productItemDtos = new HashSet<>();
    }
}

