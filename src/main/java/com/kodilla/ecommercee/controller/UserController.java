package com.kodilla.ecommercee.controller;
import com.kodilla.ecommercee.dto.UserDto;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.service.UserService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/ecommercee/user/")
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping(value = "getAllUsers")
    public List<UserDto> getAllUsers(){
        return userMapper.mapToUserDtoList(userService.getAllUsers());
    }

    @PostMapping(value = "addUser")
    public UserDto addUser(@RequestBody UserDto userDto) {
        return userMapper.mapToUserDto(userService.save(userMapper.mapToUser(userDto)));
    }

    @GetMapping(value = "getUser")
    public UserDto getUser(@RequestParam long id) {
        return userMapper.mapToUserDto(userService.getUser(id).get());
    }

    @PutMapping(value = "updateUser")
    public UserDto updateProductGroup(@RequestBody UserDto userDto) {
        return userMapper.mapToUserDto(userService.save(userMapper.mapToUser(userDto)));
    }

    @DeleteMapping(value = "deleteUser")
    public void deleteUser(@RequestParam long id) {
        userService.deleteUser(id);
    }
}
