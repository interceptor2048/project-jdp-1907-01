package com.kodilla.ecommercee.controller;
import com.kodilla.ecommercee.controller.exceptions.GroupNotFoundException;
import com.kodilla.ecommercee.domain.dto.GroupDto;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/ecommercee/productGroup/")
@CrossOrigin("*")
public class GroupController {

    @GetMapping(value = "getAllGroups")
    public List<GroupDto> getAllGroups(){
        return new ArrayList<>();
    }

    @GetMapping(value = "getGroup")
    public GroupDto getGroup(@RequestParam long id) throws GroupNotFoundException {
        return new GroupDto();
    }

    @PostMapping(value = "addGroup")
    public GroupDto addGroup(@RequestBody GroupDto groupDto) {
        return groupDto;
    }

    @PutMapping(value = "updateGroup")
    public GroupDto updateGroup(@RequestBody GroupDto groupDto) {
        return groupDto;
    }
}
