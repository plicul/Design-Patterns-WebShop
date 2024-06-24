package com.designpatternproject.service.cart.mapper;

import com.designpatternproject.dto.cart.CartDto;
import com.designpatternproject.dto.cart.CartItemDto;
import com.designpatternproject.entity.Cart;
import com.designpatternproject.entity.CartItem;

public interface CartMapper {
    public CartDto toDto(Cart cart);
    public CartItemDto cartItemToDto(CartItem item);

    CartItem cartItemDtotoCartItem(CartItemDto cartItemDto, Integer cartId);
}
