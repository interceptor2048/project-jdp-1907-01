package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.dto.ProductDto;
import com.kodilla.ecommercee.dto.ProductGroupDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class GroupMapper {
    @Autowired
    private ProductMapper productMapper;

    public Group mapToGroup(final ProductGroupDto productGroupDto) {
        List<ProductDto> productDtoList = productGroupDto.getProducts();
        List<Product> productList = productMapper.mapToProductList(productDtoList);

        return new Group(
                productGroupDto.getId(),
                productGroupDto.getName(),
                productList);
    }

    public ProductGroupDto mapToProductGroupDto(final Group group) {
        List<Product> productList = group.getProducts();
        List<ProductDto> productDtoList = productMapper.mapToProductDtoList(productList);

        return new ProductGroupDto(
                group.getId(),
                group.getName(),
                productDtoList);
    }

    public List<Group> mapToGroup(final List<ProductGroupDto> groupDtoList) {
        return groupDtoList.stream()
                .map(g -> new Group(g.getId(), g.getName(), null))
                .collect(Collectors.toList());
    }

    public List<ProductGroupDto> mapToProductGroupDto(final List<Group> groupList) {
        return groupList.stream()
                .map(g -> new ProductGroupDto(g.getId(), g.getName(), productMapper.mapToProductDtoList(g.getProducts())))
                .collect(Collectors.toList());
    }

}
