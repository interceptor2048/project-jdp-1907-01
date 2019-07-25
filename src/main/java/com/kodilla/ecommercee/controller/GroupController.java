package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.ProductGroupDto;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/ecommercee/productGroup")
public class GroupController {

    @GetMapping(value = "getProductGroups")
    public List<ProductGroupDto> getProductGroups(){
        return new ArrayList<>();
    }

    @PostMapping(value = "addProductGroup")
    public ProductGroupDto addProductGroup(@RequestBody ProductGroupDto productGroupDto) {
        return productGroupDto;
    }

    @GetMapping(value = "getProductGroupById")
    public ProductGroupDto getProductGroupById(@RequestParam int id) {
        return new ProductGroupDto();
    }

    @PutMapping(value = "updateProductGroup")
    public ProductGroupDto updateProductGroup(@RequestBody ProductGroupDto productGroupDto) {
        return productGroupDto;
    }
}
