package com.designpatternproject.service.item;

import com.designpatternproject.dto.item.ItemDto;

import java.util.List;

public interface ItemService {
    ItemDto getItemDto(Long itemId);

    List<ItemDto> getItemsForCategories(List<String> categories);

    List<ItemDto> getAllItems();

    Boolean saveItems(List<ItemDto> itemDtos);

    Boolean deleteItems(List<Long> itemIds);

    Boolean saveNewItem(ItemDto itemDto);

    boolean checkItemAvailability(Long itemId, Integer amount);

    void sellItem(Long itemId, Integer amount);
}
