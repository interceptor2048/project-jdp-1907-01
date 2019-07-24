package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.ProductDto;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {

    @GetMapping(value = "getAllProducts")
    public List<ProductDto> getAllProduct() {
        return new ArrayList<>();
    }

    @GetMapping(value = "getProductById")
    public ProductDto getProductById(@RequestParam int id) {
        return new ProductDto();
    }

    @PostMapping(value = "createProduct")
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        return productDto;
    }

    @PutMapping(value = "updateProduct")
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        return productDto;
    }

    @DeleteMapping(value = "deleteProduct")
    public void deleteProduct(@RequestParam int id) {
        //Execute productDao interface to delete object from database
    }
}
