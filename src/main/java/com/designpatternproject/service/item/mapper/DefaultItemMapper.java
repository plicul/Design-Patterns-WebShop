package com.designpatternproject.service.item.mapper;

import com.designpatternproject.dto.item.ItemDto;
import com.designpatternproject.dto.item.PriceDto;
import com.designpatternproject.entity.Item;
import org.springframework.stereotype.Component;

@Component
public class DefaultItemMapper implements ItemMapper{
    @Override
    public ItemDto toDto(Item item) {
        return new ItemDto(
                item.getId(),
                item.getItemTitle(),
                item.getItemDescription(),
                item.getItemCategory().getCategory(),
                item.getImagePath(),
                item.getPrices().stream().map(price -> {
                    return new PriceDto(price.getId(),
                            price.getPriceType().getPriceType(),
                            price.getActiveFlag(),
                            price.getAmount()
                    );
                }).toList()
        );
    }
}
