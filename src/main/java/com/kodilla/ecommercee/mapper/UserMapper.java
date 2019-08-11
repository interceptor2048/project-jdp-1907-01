package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.domain.dto.UserDto;
import com.kodilla.ecommercee.service.CartService;
import com.kodilla.ecommercee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    @Autowired
    private CartService cartService;

    public User mapToUser(UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getUsername(),
                userDto.getStatus(),
                userDto.getUserKey(),
                userDto.getPhoneNumber(),
                getCartWithId(userDto.getCartId()));
    }

    public UserDto mapToUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getStatus(),
                user.getUserKey(),
                user.getPhoneNumber(),
                user.getCart().getId());
    }

    public List<UserDto> mapToUserDtoList(List<User> userList) {
        return userList.stream()
                .map(u -> new UserDto(
                        u.getId(),
                        u.getUsername(),
                        u.getStatus(),
                        u.getUserKey(),
                        u.getPhoneNumber(),
                        u.getCart().getId()))
                .collect(Collectors.toList());
    }

    private Cart getCartWithId(Long id) {
        return cartService.getCart(id).orElse(null);
    }
}
