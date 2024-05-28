package com.designpatternproject.controller;

import com.designpatternproject.dto.menu.MenuItemDto;
import com.designpatternproject.service.menu.MenuServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("menu")
public class MenuController {

    @Autowired
    private MenuServiceImpl menuService;

    @GetMapping("/")
    public ResponseEntity<List<MenuItemDto>> getMenuItems() {
        return new ResponseEntity<>(menuService.getAllMenuItems(), HttpStatus.OK);
    }
}
