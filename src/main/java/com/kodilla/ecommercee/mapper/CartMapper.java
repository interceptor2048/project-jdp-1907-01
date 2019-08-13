package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.dto.CartDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartMapper {

    public Cart mapToCart(final CartDto cartDto) {  //fromDto
        return new Cart(
                cartDto.getId(),
                cartDto.getProductItems(),
                cartDto.getUser());
    }

    public CartDto mapToCartDto(final Cart cart) {  //fromDomain
//        CartDto cartDto = new CartDto();
//        BeanUtils.copyProperties(cart, cartDto);
//        return cartDto;
        return new CartDto(
                cart.getId(),
                cart.getProductItems(),
                cart.getUser());
    }

    public List<Cart> mapToListCart(final List<CartDto> cartDtoList) {
        return cartDtoList
                .stream()
                .map(this::mapToCart)
                .collect(Collectors.toList());
    }

    public List<CartDto> mapToListCartDto(final List<Cart> cartList) {
        return cartList
                .stream()
                .map(this::mapToCartDto)
                .collect(Collectors.toList());
    }
}
