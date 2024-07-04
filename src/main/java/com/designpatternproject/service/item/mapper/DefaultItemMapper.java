package com.designpatternproject.service.item.mapper;

import com.designpatternproject.dto.item.ItemDto;
import com.designpatternproject.dto.item.PriceDto;
import com.designpatternproject.entity.Item;
import com.designpatternproject.entity.Price;
import com.designpatternproject.repository.ItemCategoryRepository;
import com.designpatternproject.repository.ItemRepository;
import com.designpatternproject.repository.PriceRepository;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class DefaultItemMapper implements ItemMapper{

    private final ItemRepository itemRepository;
    private final ItemCategoryRepository itemCategoryRepository;
    private final PriceRepository priceRepository;

    public DefaultItemMapper(ItemRepository itemRepository,
                             ItemCategoryRepository itemCategoryRepository,
                             PriceRepository priceRepository) {
        this.itemRepository = itemRepository;
        this.itemCategoryRepository = itemCategoryRepository;
        this.priceRepository = priceRepository;
    }

    @Override
    public ItemDto toDto(Item item) {
        return new ItemDto(
                item.getId(),
                item.getItemTitle(),
                item.getItemDescription(),
                item.getItemCategory().getCategory(),
                item.getImagePath(),
                item.getQuantity(),
                item.getPrices().stream().map(price -> {
                    return new PriceDto(price.getId(),
                            price.getPriceType().getPriceType(),
                            price.getActiveFlag(),
                            price.getAmount()
                    );
                }).toList()
        );
    }

    //Only already existing items will get to here
    @Override
    public Item toEntity(ItemDto itemDto) throws Exception {
        Item item = itemRepository.findById(itemDto.getId()).orElseThrow();
        item.setItemTitle(itemDto.getItemTitle());
        item.setItemDescription(itemDto.getItemDescription());
        try {
            if(!(item.getItemCategory().getCategory().equals(itemDto.getCategory()))){
                item.setItemCategory(itemCategoryRepository.findByCategoryIgnoreCase(itemDto.getCategory()).orElseThrow());
            }
        }
        catch (Exception ignored){
        }

        item.setImagePath(itemDto.getImagePath());
        item.setQuantity(itemDto.getQuantity());
        item.setPrices(itemDto.getPrices().stream().map(priceDto -> {
            Price price = priceRepository.findById(priceDto.getId()).orElseThrow();
            price.setAmount(priceDto.getAmount());
            return price;
        }).collect(Collectors.toSet()));
        return item;
    }
}
