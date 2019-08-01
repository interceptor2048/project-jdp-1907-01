package com.kodilla.ecommercee.controller;
import com.kodilla.ecommercee.controller.exceptions.ProductNotFoundException;
import com.kodilla.ecommercee.domain.dto.ProductDto;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/ecommercee/product/")
@CrossOrigin("*")
public class ProductController {

    @GetMapping(value = "getAllProducts")
    public List<ProductDto> getAllProduct() {
        return new ArrayList<>();
    }

    @GetMapping(value = "getProduct")
    public ProductDto getProduct(@RequestParam long id)  {
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
    public void deleteProduct(@RequestParam long id) {
        //Execute productDao interface to delete object from database
    }
}
