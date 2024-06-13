package com.designpatternproject.service.item.mapper;

import com.designpatternproject.dto.item.ItemDto;
import com.designpatternproject.entity.Item;

public interface ItemMapper {
    ItemDto toDto(Item item);
}
