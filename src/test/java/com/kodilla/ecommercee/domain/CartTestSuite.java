package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.repository.CartRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
import java.util.Optional;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartTestSuite {

    @Autowired
    CartRepository cartRepository;

    private Cart createTestCart() {
        return new Cart();
    }

    @Test
    public void testSaveCart() {
        //Given
        Cart cart = createTestCart();
        long before = cartRepository.count();
        //When
        cartRepository.save(cart);
        long result = cartRepository.count() - before;
        //Then
        assertEquals(1, result);
        //CleanUp
        cartRepository.deleteById(cart.getId());
    }

    @Test
    public void testDeleteCart() {
        //Given
        Cart cart = createTestCart();
        cartRepository.save(cart);
        long startCount = cartRepository.count();
        //When
        cartRepository.deleteById(cart.getId());
        long endCount = cartRepository.count();
        //Then
        long result = startCount - endCount;
        assertEquals(1, result);
    }

    @Test
    public void testFindById() {
        //Given
        Cart cart = createTestCart();
        cartRepository.save(cart);
        //When
        Optional resultCart = cartRepository.findById(cart.getId());
        //Then
        assertTrue(Optional.ofNullable(resultCart).isPresent());
        //CleanUp
        cartRepository.deleteById(cart.getId());
    }

    @Test
    public void testFindAll() {
        //Given
        Cart cart = createTestCart();
        cartRepository.save(cart);
        long expected = cartRepository.count();
        //When
        List<Cart> resultList = cartRepository.findAll();
        //Then
        assertEquals(expected, resultList.size());
        //CleanUp
        cartRepository.deleteById(cart.getId());
    }
}
