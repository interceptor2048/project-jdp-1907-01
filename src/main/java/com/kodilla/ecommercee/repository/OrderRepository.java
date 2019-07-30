package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    
    @Override
    List<Order> findAll();
    
    @Override
    Optional<Order> findById(Long id);

    List<Order> findByUserId(Long userId);

    @Override
    Order save(Order order);

    Optional<Order> findByIsCompleted(boolean isCompleted);

    @Override
    void deleteById(Long id);
}
