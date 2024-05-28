package com.designpatternproject.controller;

import com.designpatternproject.dto.itemCategory.CategoryComponentDto;
import com.designpatternproject.service.category.ItemCategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("item-category")
public class ItemCategoryController {

    @Autowired
    private ItemCategoryServiceImpl itemCategoryServiceImpl;

    @GetMapping("/tree")
    public ResponseEntity<List<CategoryComponentDto>> getCategoryTree() {
        try {
            return new ResponseEntity<>(itemCategoryServiceImpl.getCategoryTree(), HttpStatus.OK);

        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

}
