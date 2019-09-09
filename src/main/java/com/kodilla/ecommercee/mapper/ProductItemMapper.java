package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.controller.exceptions.ProductNotFoundException;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.ProductItem;
import com.kodilla.ecommercee.domain.dto.ProductItemDto;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductItemMapper {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductRepository productRepository;

    public ProductItem mapToProductItem(final ProductItemDto productItemDto) throws ProductNotFoundException{
        Product product = productRepository.findById(productItemDto.getProductId()).orElseThrow(ProductNotFoundException::new);

        return new ProductItem(
                productItemDto.getId(),
                product,
                productItemDto.getQuantity(),
                productItemDto.getAmount());
    }

    public ProductItemDto mapToProductItemDto(final ProductItem productItem) {

        long productId = productItem.getId();
        return new ProductItemDto(
                productItem.getId(),
                productId,
                productItem.getQuantity(),
                productItem.getAmount());
    }

//    public List<ProductItem> mapToProductItemList(List<ProductItemDto> productItemDtoList) {
//        return productItemDtoList
//                .stream()
//                .map(this::mapToProductItem)
//                .collect(Collectors.toList());
//    }
//
//    public List<ProductItemDto> mapToProductItemDtoList(List<ProductItem> productItemList) {
//        return productItemList
//                .stream()
//                .map(this::mapToProductItemDto)
//                .collect(Collectors.toList());
//    }
}
