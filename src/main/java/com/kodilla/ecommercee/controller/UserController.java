package com.kodilla.ecommercee.controller;
import com.kodilla.ecommercee.dto.UserDto;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/ecommercee/user/")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @GetMapping(value = "getAllUsers")
    public List<UserDto> getAllUsers() {
        return userMapper.mapToUserDtoList(userService.getAllUsers());
    }

    @PostMapping(value = "createUser")
    public UserDto createUser(@RequestBody UserDto userDto) {
        return userMapper.mapToUserDto(userService.save(userMapper.mapToUser(userDto)));
    }

    @GetMapping(value = "getUser")
    public UserDto getUser(@RequestParam long id) throws UserNotFoundException {
        return userMapper.mapToUserDto(userService.getUser(id).orElseThrow(UserNotFoundException::new));
    }

    @PutMapping(value = "updateUser")
    public UserDto updateUser(@RequestBody UserDto userDto) {
        return userMapper.mapToUserDto(userService.save(userMapper.mapToUser(userDto)));
    }

    @DeleteMapping(value = "deleteUser")
    public void deleteUser(@RequestParam long id) {
        userService.deleteUser(id);
    }
}
