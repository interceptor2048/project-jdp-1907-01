package com.kodilla.ecommercee.controller;
import com.kodilla.ecommercee.controller.exceptions.UserNotFoundException;
import com.kodilla.ecommercee.domain.dto.UserDto;
import com.kodilla.ecommercee.mapper.UserMapper;
import com.kodilla.ecommercee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/v1/ecommercee/user/")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @GetMapping(value = "getAllUsers")
    public List<UserDto> getAllUsers() {
        return userMapper.mapToUserDtoList(userService.getAllUsers());
    }

    @GetMapping(value = "getUser")
    public UserDto getUser(@RequestParam long id) throws UserNotFoundException {
        return userMapper.mapToUserDto(userService.getUser(id).orElseThrow(UserNotFoundException::new));
    }

    @PostMapping(value = "createUser")
    public UserDto createUser(@RequestBody UserDto userDto) {
        return userMapper.mapToUserDto(userService.save(userMapper.mapToUser(userDto)));
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
