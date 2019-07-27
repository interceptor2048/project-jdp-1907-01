package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.dto.ProductDto;
import com.kodilla.ecommercee.dto.ProductGroupDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    @Autowired
    private GroupMapper groupMapper;

    public Product mapToProduct(final ProductDto productDto) {
        ProductGroupDto productGroupDto = productDto.getProductGroupDto();
        Group group = groupMapper.mapToGroup(productGroupDto);

        return new Product(
                productDto.getId(),
                productDto.getName(),
                productDto.getDescription(),
                productDto.getPrice(),
                group);
    }

    public ProductDto mapToProductDto(final Product product) {
        Group group = product.getGroup();
        ProductGroupDto productGroupDto = groupMapper.mapToProductGroupDto(group);

        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                productGroupDto);
    }

    public List<Product> mapToProductList(final List<ProductDto> productDtoList) {
        return productDtoList.stream()
                .map(p -> new Product(p.getId(), p.getName(), p.getDescription(), p.getPrice(),
                        groupMapper.mapToGroup(p.getProductGroupDto())))
                .collect(Collectors.toList());
    }

    public List<ProductDto> mapToProductDtoList(final List<Product> productList) {
        return productList.stream()
                .map(p -> new ProductDto(p.getId(), p.getName(), p.getDescription(), p.getPrice(),
                        groupMapper.mapToProductGroupDto(p.getGroup())))
                .collect(Collectors.toList());
    }
}
