package com.designpatternproject.service.cart;

import com.designpatternproject.dto.cart.CartDto;
import com.designpatternproject.dto.cart.CartItemDto;
import com.designpatternproject.dto.user.UserDto;
import com.designpatternproject.entity.Cart;
import com.designpatternproject.entity.CartItem;
import com.designpatternproject.repository.CartItemRepository;
import com.designpatternproject.repository.CartRepository;
import com.designpatternproject.service.cart.mapper.CartMapperImpl;
import com.designpatternproject.service.item.ItemService;
import com.designpatternproject.service.item.ItemServiceImpl;
import com.designpatternproject.service.user.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CartServiceImpl implements CartService{

    private final CartRepository cartRepository;
    private final CartMapperImpl cartMapper;
    private final CartItemRepository cartItemRepository;
    private final UserServiceImpl userService;
    private final ItemServiceImpl itemService;


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
        List<CartItemDto> cartItemDtoList = cartDto.getCartItems().stream().filter(cartItemDto -> cartItemDto.getAmount() < 1).toList();
        cartItemRepository.deleteAllById(cartItemDtoList.stream().map(CartItemDto::getId).toList());

        savedCart.setCartItems(cartDto.getCartItems().stream().filter(cartItemDto -> cartItemDto.getAmount() > 0).map(cartItemDto -> cartMapper.cartItemDtotoCartItem(cartItemDto,savedCart.getId())).collect(Collectors.toSet()));

        cartRepository.save(savedCart);

        return true;
    }

    @Override
    public String checkoutCart(CartDto cartDto, String userName) throws Exception {
        Long totalPrice = 0L;
        for (CartItemDto cartItemDto:
                cartDto.getCartItems()) {
            if(!itemService.checkItemAvailability(cartItemDto.getItemId(),cartItemDto.getAmount()))
                throw new Exception("Items Are Not Available!");
            totalPrice += cartItemDto.getAmount()*cartItemDto.getItemPrice();
        }

        if(!userService.checkCash(userName,totalPrice))
            throw new Exception("User Dosen't Have Enough Money To Checkout!");

        userService.pay(userName,totalPrice);
        for (CartItemDto cartItemDto:
                cartDto.getCartItems()) {
            itemService.sellItem(cartItemDto.getItemId(),cartItemDto.getAmount());
        }

        return "Checkout Successfully Completed!";
    }
}
