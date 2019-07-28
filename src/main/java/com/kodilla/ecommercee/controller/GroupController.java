package com.kodilla.ecommercee.controller;
import com.kodilla.ecommercee.domain.dto.GroupDto;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/ecommercee/productGroup/")
public class GroupController {

    @GetMapping(value = "getProductGroups")
    public List<GroupDto> getProductGroups(){
        return new ArrayList<>();
    }

    @PostMapping(value = "addProductGroup")
    public GroupDto addProductGroup(@RequestBody GroupDto groupDto) {
        return groupDto;
    }

    @GetMapping(value = "getProductGroupById")
    public GroupDto getProductGroupById(@RequestParam long id) {
        return new GroupDto();
    }

    @PutMapping(value = "updateProductGroup")
    public GroupDto updateProductGroup(@RequestBody GroupDto groupDto) {
        return groupDto;
    }
}
