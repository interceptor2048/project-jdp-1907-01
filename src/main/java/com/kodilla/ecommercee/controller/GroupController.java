package com.kodilla.ecommercee.controller;
import com.kodilla.ecommercee.dto.ProductGroupDto;
import com.kodilla.ecommercee.mapper.GroupMapper;
import com.kodilla.ecommercee.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/v1/ecommercee/productGroup/")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private GroupMapper groupMapper;

    @GetMapping(value = "getProductGroups")
    public List<ProductGroupDto> getProductGroups(){
        return groupMapper.mapToroductGroupDtoList(groupService.getAllGroup());
    }

    @GetMapping(value = "getProductGroupById/{groupId}")
    public ProductGroupDto getProductGroupById(@RequestParam Long groupId) throws GroupNotFoundException{
        return groupMapper.mapToProductGroupDto(groupService.getGroup(groupId).orElseThrow(GroupNotFoundException::new));
    }

    @PostMapping(value = "addProductGroup", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addProductGroup(@RequestBody ProductGroupDto productGroupDto) {
        groupService.saveGroup(groupMapper.mapToGroup(productGroupDto));
    }

    @PutMapping(value = "updateProductGroup")
    public ProductGroupDto updateProductGroup(@RequestBody ProductGroupDto productGroupDto) {
        return groupMapper.mapToProductGroupDto(groupService.saveGroup(groupMapper.mapToGroup(productGroupDto)));
    }

}
