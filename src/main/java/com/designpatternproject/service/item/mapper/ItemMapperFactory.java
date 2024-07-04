package com.designpatternproject.service.item.mapper;

import com.designpatternproject.dto.item.ItemDto;
import com.designpatternproject.entity.Item;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ItemMapperFactory {
    private final DefaultItemMapper defaultItemMapper;
    private final NewItemMapper newItemMapper;


    public ItemMapper getMapper(Item item) {

        if (false /*TODO CASE FOR ALTERNATIVE MAPPER*/) {
            //return specialItemMapper;
        }

        return defaultItemMapper;
    }
    public ItemMapper getMapper(ItemDto itemDto) {

        if (itemDto.getId() == null) {
            return newItemMapper;
        }

        return defaultItemMapper;
    }
}
