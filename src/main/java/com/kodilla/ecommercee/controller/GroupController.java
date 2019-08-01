package com.kodilla.ecommercee.controller;
import com.kodilla.ecommercee.controller.exceptions.GroupNotFoundException;
import com.kodilla.ecommercee.domain.dto.GroupDto;
import com.kodilla.ecommercee.mapper.GroupMapper;
import com.kodilla.ecommercee.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/v1/ecommercee/productGroup/")
@CrossOrigin("*")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private GroupMapper groupMapper;

    @GetMapping(value = "getAllGroups")
    public List<GroupDto> getAllGroups(){
        return groupMapper.mapToGroupDtoList(groupService.getAllGroups());
    }

    @GetMapping(value = "getGroup/{groupId}")
    public GroupDto getGroup(@RequestParam long id) throws GroupNotFoundException {
        return groupMapper.mapToGroupDto(groupService.getGroup(id).orElseThrow(GroupNotFoundException::new));
    }

    @PostMapping(value = "addGroup", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addGroup(@RequestBody GroupDto groupDto) {
        groupService.saveGroup(groupMapper.mapToGroup(groupDto));
    }

    @PutMapping(value = "updateGroup")
    public GroupDto updateGroup(@RequestBody GroupDto groupDto) {
        return groupMapper.mapToGroupDto(groupService.saveGroup(groupMapper.mapToGroup(groupDto)));
    }
}
