package com.designpatternproject.controller;

import com.designpatternproject.dto.cart.CartDto;
import com.designpatternproject.service.cart.CartService;
import com.designpatternproject.service.cart.CartServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("cart")
public class CartController {

    CartServiceImpl cartService;

    @GetMapping("/{id}")
    public ResponseEntity<CartDto> getCart(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(cartService.getCart(id), HttpStatus.OK);

        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Integer> createCart(@RequestBody CartDto cartDto) {
        try {
            return new ResponseEntity<>(cartService.createNewCart(cartDto), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/")
    public ResponseEntity<Boolean> updateCart( @RequestBody CartDto cartDto) {
        try {
            return new ResponseEntity<>(cartService.updateCart(cartDto), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(false, HttpStatus.NO_CONTENT);
        }
    }


    }
