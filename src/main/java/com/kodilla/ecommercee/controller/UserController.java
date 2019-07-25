package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.UserDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/ecommercee/user/")
public class UserController {

    @GetMapping(value = "getAllUsers")
    public List<UserDto> getAllUsers(){
        return new ArrayList<>();
    }

    @PostMapping(value = "addUser")
    public UserDto addUser(@RequestBody UserDto UserDto) {
        return UserDto;
    }

    @GetMapping(value = "getUser")
    public UserDto getUser(@RequestParam long id) {
        return new UserDto();
    }

    @PutMapping(value = "updateUser")
    public UserDto updateProductGroup(@RequestBody UserDto UserDto) {
        return UserDto;
    }

    @DeleteMapping(value = "deleteUser")
    public void deleteUser(@RequestParam long id) {
    }
}
