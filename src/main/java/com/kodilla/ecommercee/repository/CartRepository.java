package com.kodilla.ecommercee.repository;
import com.kodilla.ecommercee.domain.Cart;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface CartRepository extends CrudRepository<Cart, Long> {


    @Override
    Cart findByUserId(Long userId);

    @Override
    Optional<Cart> findById(Long productId);

    @Override
    Cart save(Cart cart);

    @Override
    void deleteById(Long productId);

    @Override
    Cart findByCartId(Long cartId);
}