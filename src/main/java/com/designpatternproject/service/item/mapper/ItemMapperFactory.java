package com.designpatternproject.service.item.mapper;

import com.designpatternproject.entity.Item;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ItemMapperFactory {
    private final DefaultItemMapper defaultItemMapper;
    //private final SpecialItemMapper specialItemMapper;


    public ItemMapper getMapper(Item item) {

        if (false /*TODO CASE FOR ALTERNATIVE MAPPER*/) {
            //return specialItemMapper;
        }

        return defaultItemMapper;
    }
}
