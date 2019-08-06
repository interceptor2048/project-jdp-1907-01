package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.domain.dto.ProductDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    public void shouldMapToProductList() {
        //Given
        List<ProductDto> productDtos = new ArrayList<>(Arrays.asList(getProductDto()));
        //When
        List<Product> resultProducts = productMapper.mapToProductList(productDtos);
        ProductDto productDto = productDtos.get(0);
        Product product = resultProducts.get(0);
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
        List<ProductDto> productDtos = productMapper.mapToProductDtoList(products);
        ProductDto productDto = productDtos.get(0);
        Product product = products.get(0);
        //Then
        assertEquals(products.size(),productDtos.size());
        assertEquals(productDto.getName(), product.getName());
        assertEquals(productDto.getId(), product.getId());
    }
}