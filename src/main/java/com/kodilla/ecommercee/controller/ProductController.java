package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.ProductDto;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductService productService;

    @GetMapping(value = "getAllProducts")
    public List<ProductDto> getAllProduct() {
        //return new ArrayList<>();
        return productMapper.mapToProductDtoList(productService.getAllProduct());
    }

    @GetMapping(value = "getProductById")
    public ProductDto getProductById(@RequestParam int id) throws ProductNotFoundException {
        //return new ProductDto();
        return productMapper.mapToProductDto(productService.getProductById(id).orElseThrow(ProductNotFoundException::new));
    }

    @PostMapping(value = "createProduct")
    public void createProduct(@RequestBody ProductDto productDto) {       //public ProductDto createProduct
        //return productDto;
        productService.saveProduct(productMapper.mapToProduct(productDto));
    }

    @PutMapping(value = "updateProduct")
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        //return productDto;
        return productMapper.mapToProductDto(productService.saveProduct(productMapper.mapToProduct(productDto)));
    }

    @DeleteMapping(value = "deleteProduct")
    public void deleteProduct(@RequestParam int id) {
        //Execute productDao interface to delete object from database
        productService.deleteProduct(id);
    }
}
