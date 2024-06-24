package com.designpatternproject.dto.cart;

import com.designpatternproject.entity.Cart;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link Cart}
 */

@Value
public class CartDto implements Serializable {
    Integer id;
    Integer status;
    Set<CartItemDto> cartItems;
}