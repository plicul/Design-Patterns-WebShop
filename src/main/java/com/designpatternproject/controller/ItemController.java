package com.designpatternproject.controller;

import com.designpatternproject.dto.item.ItemDto;
import com.designpatternproject.entity.Item;
import com.designpatternproject.service.item.ItemServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("item")
public class ItemController {
    private final ItemServiceImpl itemService;

    @GetMapping("/")
    public ResponseEntity<?> getItem(@RequestParam(required = false) Long itemId, @RequestParam(required = false) List<String> categories){
        try {
            if(itemId != null)
                return new ResponseEntity<>(itemService.getItemDto(itemId), HttpStatus.OK);
            if(categories != null)
                return new ResponseEntity<>(itemService.getItemsForCategories(categories),HttpStatus.OK);
            return new ResponseEntity<>(itemService.getAllItems(),HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }
}
