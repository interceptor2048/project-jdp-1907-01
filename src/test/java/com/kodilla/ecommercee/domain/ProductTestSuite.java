package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductTestSuite {

    private Logger LOGGER = LoggerFactory.getLogger(ProductTestSuite.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private GroupRepository groupRepository;

    public Product createContent() {
        Group group = new Group("Nike");
        groupRepository.save(group);
        return new Product(
                "Buty Nike", "Fajne buty",
                new BigDecimal(100), group);
    }

    @Test
    public void shouldAddToDatabase() {
        //Given
        Product product = createContent();
        productRepository.save(product);

        //When
        long numberOfRecords = productRepository.count();

        //Then
        assertEquals(1, numberOfRecords);

        //Code clean up
        productRepository.deleteById(product.getId());
        groupRepository.deleteById(product.getGroup().getId());
    }

    @Test
    public void shouldDeleteFromDatabase() {
        //Given
        Product product = createContent();
        productRepository.save(product);
        LOGGER.info("Records in table: " + productRepository.count());
        productRepository.deleteById(product.getId());
        LOGGER.info("Records in table: " + productRepository.count());

        //When
        long numberOfRecords = productRepository.count();

        //Then
        assertEquals(0,numberOfRecords);
    }

    @Test
    public void shouldFindProductById() {
        //Given
        Product product = createContent();
        productRepository.save(product);

        //When
        Optional resultProduct = productRepository.findById(product.getId());

        //Then
        assertTrue(Optional.ofNullable(resultProduct).isPresent());

        //Clean Up
        productRepository.deleteById(product.getId());
        groupRepository.deleteById(product.getGroup().getId());
    }

    @Test
    public void shouldUpdateProduct() {
        //Given
        Product product = createContent();
        productRepository.save(product);

        Group group = new Group("Addidas");
        groupRepository.save(group);

        Product updateProduct = new Product(
                product.getId(), "Buty Addidas",
                "Drogie buty", new BigDecimal(600), group );
        productRepository.save(updateProduct);

        //When
        List<Product> resultListOfProducts = productRepository.findAll();
        Product resultProduct = resultListOfProducts.get(0);

        //Then
        assertEquals(1, resultListOfProducts.size());
        assertEquals("Buty Addidas",resultProduct.getName());
        assertEquals("Drogie buty",resultProduct.getDescription());
        assertEquals(600, resultProduct.getPrice().intValue());
        assertEquals("Addidas", resultProduct.getGroup().getName());

        //Clean Up
        productRepository.deleteById(product.getId());
    }
}