package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.controller.GroupNotFoundException;
import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.dto.ProductDto;
import com.kodilla.ecommercee.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    @Autowired
    private GroupService groupService;

    public Product mapToProduct (final ProductDto productDto) {
        return new Product(
                productDto.getId(),
                productDto.getName(),
                productDto.getDescription(),
                productDto.getPrice(),
                getGroupId(productDto.getGroupId()));   // groupId z Group
    }

    public ProductDto mapToProductDto(final Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                getGroupIdForProduct(product.getGroup()));  // groupId z Group
    }

    public List<Product> mapToProductList(final List<ProductDto> productDtoList){
        return productDtoList
                .stream()
                .map(this::mapToProduct)
                .collect(Collectors.toList());
    }

    public List<ProductDto> mapToProductDtoList(final List<Product> productList) {
        return productList
                .stream()
                //.map(p -> new ProductDto(p.getId(), p.getName(), p.getDescription(), p.getPrice(), null))
                .map(this::mapToProductDto)
                .collect(Collectors.toList());
    }

    private Group getGroupId(Long id) {     // wyszukanie GroupID w serwisie
        if (id == null) {
            return null;
        } else {
            return groupService.getGroupById(id);
        }
    }

    private Long getGroupIdForProduct(Group group) {
        try {
            return group.getId();
        } catch (Exception e) {
            return null;
        }
    }
}
