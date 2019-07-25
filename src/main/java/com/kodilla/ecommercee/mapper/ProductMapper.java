package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.dto.Product;
import com.kodilla.ecommercee.dto.ProductDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {
    public Product mapToProduct(final ProductDto productDto) {
        return new Product(
                productDto.getId(),
                productDto.getName(),
                productDto.getDescription(),
                productDto.getPrice(),
                productDto.getGroupId());
    }

    public ProductDto mapToProductDto(final Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getGroupId());
    }

    public List<Product> mapToProduct(final  List<ProductDto> productDtoList) {
        return productDtoList.stream()
                .map(p -> new Product(p.getId(), p.getName(), p.getDescription(), p.getPrice(), p.getGroupId()))
                .collect(Collectors.toList());
    }

    public List<ProductDto> mapToProductDtoList(final List<Product> productList) {
        return productList.stream()
                .map(p -> new ProductDto(p.getId(), p.getName(), p.getDescription(), p.getPrice(), p.getGroupId()))
                .collect(Collectors.toList());
    }
}
