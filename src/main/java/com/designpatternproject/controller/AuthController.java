package com.designpatternproject.controller;

import com.designpatternproject.dto.security.JwtAuthResponse;
import com.designpatternproject.dto.security.LoginDto;
import com.designpatternproject.service.security.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("auth")
public class AuthController {

    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto){
        String token = authService.login(loginDto);
        if(token == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);

        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(token);
        jwtAuthResponse.setUserName(loginDto.getUsername());

        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Boolean> register(@RequestBody LoginDto loginDto){
        try {
            authService.register(loginDto);
        }
        catch (Exception e){
            return new ResponseEntity<>(false, HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<>(true, HttpStatus.OK);
    }

}