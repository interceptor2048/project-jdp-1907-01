package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.ProductItem;
import com.kodilla.ecommercee.domain.dto.ProductDto;
import com.kodilla.ecommercee.domain.dto.ProductItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ProductItemMapper {

    @Autowired
    private ProductMapper productMapper;

    public ProductItem mapToProductItem(final ProductItemDto productItemDto) {
        Set<ProductDto> productDtos = productItemDto.getProducts();
        Set<Product> products = productMapper.mapToProductSet(productDtos);

        return new ProductItem(
                productItemDto.getId(),
                products,
                productItemDto.getQuantity(),
                productItemDto.getAmmount());
    }

    public ProductItemDto mapToProductItemDto(final ProductItem productItem) {


        Set<Product> products = productItem.getProducts();
        Set<ProductDto> productDtos = productMapper.mapToProductDtoSet(products);
        return new ProductItemDto(
                productItem.getId(),
                productDtos,
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
