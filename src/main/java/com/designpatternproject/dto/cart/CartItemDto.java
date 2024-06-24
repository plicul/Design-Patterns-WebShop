package com.designpatternproject.dto.cart;

import com.designpatternproject.entity.CartItem;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link CartItem}
 */
@Value
public class CartItemDto implements Serializable {
    Integer id;
    Long itemId;
    String itemItemTitle;
    String itemImagePath;
    Long itemPrice;
    Integer cartId;
    @NotNull
    Integer amount;
}