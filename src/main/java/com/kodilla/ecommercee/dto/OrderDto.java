package com.kodilla.ecommercee.dto;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OrderDto {

    private Long id;
    private LocalDate date;
    private boolean isCompleted;
    private boolean isCompleted;
    @OneToMany(targetEntity = Product.class, mappedBy="order", fetch=FetchType.LAZY)
    private List<Product> productList;
    private Long userId;
}
