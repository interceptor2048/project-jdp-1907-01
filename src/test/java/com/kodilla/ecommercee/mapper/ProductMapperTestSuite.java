package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.dto.ProductDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductMapperTestSuite {

    @Autowired
    ProductMapper productMapper;

    private Product getProduct() {
        return new Product(1L, "Shoes", "Cofortable shoes", new BigDecimal(10000), new Group(45L, "Clothes"));
    }

    private ProductDto getProductDto() {
        return new ProductDto(52L, "jacket", "cheap jacket", new BigDecimal(50), 40L);
    }

    @Test
    public void shouldMapToProduct() {
        //Given
        ProductDto productDto = getProductDto();
        //When
        Product resultProduct = productMapper.mapToProduct(productDto);
        //Then
        assertEquals(productDto.getId(), resultProduct.getId());
        assertEquals(productDto.getPrice(), resultProduct.getPrice());
        assertEquals(productDto.getName(), resultProduct.getName());
    }

    @Test
    public void shouldMapToProductDto() {
        //Given
        Product product = getProduct();
        //When
        ProductDto productDto = productMapper.mapToProductDto(product);
        //Then
        assertEquals(product.getId(), productDto.getId());
        assertEquals(product.getName(), productDto.getName());
    }

    @Test
    public void shouldMapToProductSet() {
        //Given
        List<ProductDto> productDtos = new ArrayList<>(Arrays.asList(getProductDto()));
        //When
        Set<Product> resultProducts = productMapper.mapToProductSet(productDtos.stream().collect(Collectors.toSet()));
        ProductDto productDto = productDtos.get(0);
        Product product = null;
        Iterator<Product> iterator = resultProducts.iterator();
        while(iterator.hasNext()) {
             product = iterator.next();
             if(product.equals(getProduct())) {
                 break;
             }
        }
        //Then
        assertEquals(productDtos.size(), resultProducts.size());
        assertEquals(productDto.getName(), product.getName());
        assertEquals(productDto.getId(), product.getId());
    }

    @Test
    public void shouldMapToProductDtoList() {
        //Given
        List<Product> products = new ArrayList<>(Arrays.asList(getProduct()));
        //When
        Set<ProductDto> productDtos = productMapper.mapToProductDtoSet(products.stream().collect(Collectors.toSet()));
        ProductDto productDto = null;
        Product product = products.get(0);
        Iterator<ProductDto> iterator = productDtos.iterator();
        while(iterator.hasNext()) {
            productDto = iterator.next();
            if(productDto.equals(getProductDto())) {
                break;
            }
        }
        //Then
        assertEquals(products.size(),productDtos.size());
        assertEquals(productDto.getName(), product.getName());
        assertEquals(productDto.getId(), product.getId());
    }
}