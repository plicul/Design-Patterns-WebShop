package com.designpatternproject.service.cart;

import com.designpatternproject.dto.cart.CartDto;

public interface CartService {
    CartDto getCart(Integer id) throws Exception;

    Integer createNewCart(CartDto cartDto);

    Boolean updateCart(CartDto cartDto);

    String checkoutCart(CartDto cartDto, String userName) throws Exception;
}
