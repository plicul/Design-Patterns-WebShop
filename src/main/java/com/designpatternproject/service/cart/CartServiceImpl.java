package com.designpatternproject.service.cart;

import com.designpatternproject.dto.cart.CartDto;
import com.designpatternproject.entity.Cart;
import com.designpatternproject.repository.CartRepository;
import com.designpatternproject.service.cart.mapper.CartMapperImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CartServiceImpl implements CartService{

    private final CartRepository cartRepository;
    private final CartMapperImpl cartMapper;



    @Override
    public CartDto getCart(Integer id) throws Exception{
        Optional<Cart> optionalCart = cartRepository.findById(id);
        Cart cart = optionalCart.get();
        return cartMapper.toDto(cart);

    }

    @Override
    public Integer createNewCart(CartDto cartDto) {
        //TODO
        //get user
        //ako postoji user spremi ga u usera

        Cart cart = new Cart();
        cart.setStatus(1);
        //cart.setUser(optionalUser.get());
        Cart savedCart = cartRepository.saveAndFlush(cart);

        savedCart.setCartItems(cartDto.getCartItems().stream().map(cartItemDto ->  cartMapper.cartItemDtotoCartItem(cartItemDto,savedCart.getId())).collect(Collectors.toSet()));

        Cart savedItemsCart =  cartRepository.saveAndFlush(savedCart);

        //return cartMapper.toDto(savedItemsCart);
        return savedItemsCart.getId();
    }

    @Override
    public Boolean updateCart(CartDto cartDto) {
        //TODO
        Optional<Cart> optionalCart = cartRepository.findById(cartDto.getId());
        if (optionalCart.isEmpty()) {
            return false;
        }
        Cart cart = optionalCart.get();
        cart.setStatus(cartDto.getStatus());
        Cart savedCart = cartRepository.saveAndFlush(cart);

        savedCart.setCartItems(cartDto.getCartItems().stream().map(cartItemDto ->  cartMapper.cartItemDtotoCartItem(cartItemDto,savedCart.getId())).collect(Collectors.toSet()));

        cartRepository.save(savedCart);

        return true;
    }
}
