package com.designpatternproject.service.cart.mapper;

import com.designpatternproject.dto.cart.CartDto;
import com.designpatternproject.dto.cart.CartItemDto;
import com.designpatternproject.dto.item.ItemDto;
import com.designpatternproject.entity.Cart;
import com.designpatternproject.entity.CartItem;
import com.designpatternproject.entity.Item;
import com.designpatternproject.repository.CartRepository;
import com.designpatternproject.repository.ItemRepository;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CartMapperImpl implements CartMapper {

    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;

    public CartMapperImpl(ItemRepository itemRepository,
                          CartRepository cartRepository) {
        this.itemRepository = itemRepository;
        this.cartRepository = cartRepository;
    }

    @Override
    public CartDto toDto(Cart cart) {
        CartDto cartDto = new CartDto(
                cart.getId(),
                cart.getStatus(),
                cart.getCartItems().stream().map(this::cartItemToDto).collect(Collectors.toSet())
        );
        return cartDto;
    }

    @Override
    public CartItemDto cartItemToDto(CartItem item) {
        CartItemDto cartItemDto = new CartItemDto(
                item.getId(),
                item.getItem().getId(),
                item.getItem().getItemTitle(),
                item.getItem().getImagePath(),
                item.getItem().getPrices().stream().findFirst().get().getAmount(),
                item.getCart().getId(),
                item.getAmount()
        );
        return cartItemDto;
    }

    @Override
    public CartItem cartItemDtotoCartItem(CartItemDto cartItemDto, Integer cartId) {
        //TODO
        CartItem cartItem = new CartItem();
        Item item = itemRepository.findById(cartItemDto.getItemId()).get();
        if(cartItemDto.getId() != null)
            cartItem.setId(cartItemDto.getId());
        cartItem.setItem(item);
        cartItem.setAmount(cartItemDto.getAmount());
        cartItem.setCart(cartRepository.findById(cartId).get());
        return cartItem;
    }
}
