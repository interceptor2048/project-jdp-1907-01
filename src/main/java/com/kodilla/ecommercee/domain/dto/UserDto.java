package com.kodilla.ecommercee.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserDto {
    private Long id;
    private String username;
    private String status;
    private Long userKey;
    private String address;
    private String email;
    private String phoneNumber;
    private Long cartId;
}
