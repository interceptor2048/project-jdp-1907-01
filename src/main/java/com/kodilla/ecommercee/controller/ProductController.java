package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.ProductDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {

    @RequestMapping(method = RequestMethod.GET, value = "getAllProducts")
    public List<ProductDto> getAllProduct() {
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.GET, value = "getProductById")
    public ProductDto getProductById(@RequestParam int id) {
        return new ProductDto();
    }

    @RequestMapping(method = RequestMethod.POST, value = "createProduct")
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        return productDto;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateProduct")
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        return productDto;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteProduct")
    public void deleteProduct(@RequestParam int id) {
        //Execute productDao interface to delete object from database
    }

}
