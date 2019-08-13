package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.ProductItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductItemRepository extends CrudRepository<ProductItem,Long> {

    @Override
    List<ProductItem> findAll();

    @Override
    Optional<ProductItem> findById(Long id);

    @Override
    ProductItem save(ProductItem productItem);

    @Override
    void deleteById(Long id);
}
