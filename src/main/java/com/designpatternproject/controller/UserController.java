package com.designpatternproject.controller;

import com.designpatternproject.dto.menu.MenuItemDto;
import com.designpatternproject.dto.user.UserDto;
import com.designpatternproject.service.menu.MenuServiceImpl;
import com.designpatternproject.service.user.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ResponseBody
@RequestMapping("user")
@AllArgsConstructor
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    //preauthorize sa nekom rolom ako cemo ovo imat
    /*
    @GetMapping("/")
    public ResponseEntity<List<MenuItemDto>> getMenuItems() {
        return new ResponseEntity<>(menuService.getAllMenuItems(), HttpStatus.OK);
    }
     */
    @GetMapping("/")
    public ResponseEntity<UserDto> getUser(@RequestParam String userName) {
        try {
            return new ResponseEntity<>(userService.getUserDto(userName), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }
    @PutMapping("/")
    public ResponseEntity<Boolean> updateUser (@RequestBody UserDto userDto ){
        try {
            userService.updateUser(userDto);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }
}
