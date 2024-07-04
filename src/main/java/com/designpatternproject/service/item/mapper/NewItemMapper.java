package com.designpatternproject.service.item.mapper;

import com.designpatternproject.dto.item.ItemDto;
import com.designpatternproject.dto.item.PriceDto;
import com.designpatternproject.entity.Item;
import com.designpatternproject.entity.Price;
import com.designpatternproject.repository.ItemCategoryRepository;
import com.designpatternproject.repository.ItemRepository;
import com.designpatternproject.repository.PriceRepository;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.stream.Collectors;
@Component
public class NewItemMapper implements ItemMapper{

    private final ItemRepository itemRepository;
    private final ItemCategoryRepository itemCategoryRepository;
    private final PriceRepository priceRepository;

    public NewItemMapper(ItemRepository itemRepository,
                             ItemCategoryRepository itemCategoryRepository,
                             PriceRepository priceRepository) {
        this.itemRepository = itemRepository;
        this.itemCategoryRepository = itemCategoryRepository;
        this.priceRepository = priceRepository;
    }

    @Override
    public ItemDto toDto(Item item) throws Exception {
        throw new Exception("invalid operation");
    }

    @Override
    public Item toEntity(ItemDto itemDto) {
        Item item = new Item();
        item.setItemTitle(itemDto.getItemTitle());
        item.setItemDescription(itemDto.getItemDescription());
        try {
            if(!(item.getItemCategory().getCategory().equals(itemDto.getCategory()))){
                item.setItemCategory(itemCategoryRepository.findByCategoryIgnoreCase(itemDto.getCategory()).orElseThrow());
            }
        }
        catch (Exception ignored){
            item.setItemCategory(itemCategoryRepository.findByCategoryIgnoreCase("CATEGORY 1").orElseThrow());
        }
        item.setImagePath(itemDto.getImagePath());
        item.setQuantity(itemDto.getQuantity());
        /*item.setPrices(itemDto.getPrices().stream().map(priceDto -> {
            //construct new price obj
            Price price = new Price();
            price.setAmount(priceDto.getAmount());
            return price;
        }).collect(Collectors.toSet()));*/
        item.setCreationDate(new Date());
        return item;
    }
}
