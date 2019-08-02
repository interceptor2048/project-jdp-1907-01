package com.kodilla.ecommercee.controller;
import com.kodilla.ecommercee.controller.exceptions.ProductNotFoundException;
import com.kodilla.ecommercee.domain.dto.ProductDto;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/v1/ecommercee/product/")
@CrossOrigin("*")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductMapper productMapper;

    @GetMapping(value = "getAllProducts")
    public List<ProductDto> getAllProducts() {
        return productMapper.mapToProductDtoList(productService.getAllProducts());
    }

    @GetMapping(value = "getProduct")

    public ProductDto getProduct(@RequestParam long id) throws ProductNotFoundException {
        return productMapper.mapToProductDto(productService.getProduct(id).orElseThrow(ProductNotFoundException::new));
    }

    @PostMapping(value = "createProduct", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createProduct(@RequestBody ProductDto productDto) {
        productService.saveProduct(productMapper.mapToProduct(productDto));
    }

    @PutMapping(value = "updateProduct")
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        return productMapper.mapToProductDto(productService.saveProduct(productMapper.mapToProduct(productDto)));
    }

    @DeleteMapping(value = "deleteProduct")
    public void deleteProduct(@RequestParam long id) {
        productService.deleteProduct(id);
    }
}
