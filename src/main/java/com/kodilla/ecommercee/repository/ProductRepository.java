package com.kodilla.ecommercee.repository;
import com.kodilla.ecommercee.domain.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findAll();

    @Override
    Optional<Product> findById(Long id);

    @Override
    Product save(Product product);

    @Override
    void deleteById(Long id);
}
