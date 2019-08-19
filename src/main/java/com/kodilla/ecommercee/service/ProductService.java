package com.kodilla.ecommercee.service;
import com.kodilla.ecommercee.controller.exceptions.ProductNotFoundException;
import com.kodilla.ecommercee.domain.Group;
import com.kodilla.ecommercee.domain.GroupType;
import com.kodilla.ecommercee.domain.Product;
import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private GroupService groupService;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProduct(final Long id)  {
        return productRepository.findById(id);
    }

    public Product saveProduct(final Product product) {
        Optional<Group> groupOptional = groupService.getUnassignedProductGroup();
        groupOptional.ifPresent(product::setGroup);
        return productRepository.save(product);
    }

    public void deleteProduct(final Long id) {
        productRepository.deleteById(id);
    }

    public Optional<Product> findProductByName(final String name) {
        return productRepository.findProductByName(name);
    }
}
