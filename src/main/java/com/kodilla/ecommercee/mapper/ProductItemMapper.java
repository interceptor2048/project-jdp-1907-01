package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.ProductItem;
import com.kodilla.ecommercee.domain.dto.ProductItemDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductItemMapper {

    public ProductItem mapToProductItem(final ProductItemDto productItemDto) {
        return new ProductItem(
                productItemDto.getId(),
                productItemDto.getProducts(),
                productItemDto.getQuantity(),
                productItemDto.getAmmount());
    }

    public ProductItemDto mapToProductItemDto(final ProductItem productItem) {
        return new ProductItemDto(
                productItem.getId(),
                productItem.getProducts(),
                productItem.getQuantity(),
                productItem.getAmmount());
    }

    public List<ProductItem> mapToProductItemList(List<ProductItemDto> productItemDtoList) {
        return productItemDtoList
                .stream()
                .map(this::mapToProductItem)
                .collect(Collectors.toList());
    }

    public List<ProductItemDto> mapToProductItemDtoList(List<ProductItem> productItemList) {
        return productItemList
                .stream()
                .map(this::mapToProductItemDto)
                .collect(Collectors.toList());
    }
}
