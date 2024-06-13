package com.designpatternproject.service.item;

import com.designpatternproject.dto.item.ItemDto;
import com.designpatternproject.entity.Item;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ItemService {
    ItemDto getItemDto(Long itemId);

    List<ItemDto> getItemsForCategories(List<String> categories);

    List<ItemDto> getAllItems();
}
