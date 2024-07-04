package com.designpatternproject.controller;

import com.designpatternproject.dto.item.ItemDto;
import com.designpatternproject.service.item.ItemServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("item")
public class ItemController {
    private final ItemServiceImpl itemService;

    @GetMapping("/")
    public ResponseEntity<?> getItem(@RequestParam(required = false) Long itemId, @RequestParam(required = false) String categories){
        try {
            if(itemId != null)
                return new ResponseEntity<>(itemService.getItemDto(itemId), HttpStatus.OK);
            if(categories != null)
                return new ResponseEntity<>(itemService.getItemsForCategories(Arrays.stream(categories.split("_")).toList()),HttpStatus.OK);
            return new ResponseEntity<>(itemService.getAllItems(),HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Boolean> postItem(@RequestBody ItemDto itemDto){
        try {
            return new ResponseEntity<>(itemService.saveNewItem(itemDto),HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }
    @PutMapping("/")
    public ResponseEntity<Boolean> putItem(@RequestBody List<ItemDto> itemDtos){
        try {
            return new ResponseEntity<>(itemService.saveItems(itemDtos),HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/")
    public ResponseEntity<?> deleteItems(@RequestBody List<Long> itemIds){
        try {
            return new ResponseEntity<>(itemService.deleteItems(itemIds),HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }
}
