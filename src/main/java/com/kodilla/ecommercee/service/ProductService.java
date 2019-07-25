package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.dto.Product;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Product> getAllProduct() {
        return repository.findAll();
    }

    public Optional<Product> getProductById(final Integer id){
        return repository.findById(id);
    }

    public Product saveProduct(final Product product) {
        return repository.save(product);
    }

    public  void deleteProduct(final Integer id) {
        repository.deleteById(id);
    }
}
