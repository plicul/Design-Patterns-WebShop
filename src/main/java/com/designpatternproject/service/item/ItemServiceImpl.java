package com.designpatternproject.service.item;

import com.designpatternproject.dto.item.ItemDto;
import com.designpatternproject.entity.Item;
import com.designpatternproject.entity.ItemCategory;
import com.designpatternproject.repository.ItemCategoryRepository;
import com.designpatternproject.repository.ItemRepository;
import com.designpatternproject.service.category.ItemCategoryServiceImpl;
import com.designpatternproject.service.item.mapper.ItemMapper;
import com.designpatternproject.service.item.mapper.ItemMapperFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ItemServiceImpl implements ItemService{

    private final ItemRepository itemRepository;
    private final ItemMapperFactory itemMapperFactory;
    private final ItemCategoryServiceImpl itemcategoryService;

    @Override
    public ItemDto getItemDto(Long itemId) {
        try {
            Item item = itemRepository.findById(itemId).orElseThrow();
            ItemMapper itemMapper = itemMapperFactory.getMapper(item);
            return itemMapper.toDto(item);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<ItemDto> getItemsForCategories(List<String> categories) {
        try {
            //get all subcategories
            List<ItemCategory> allCategories = itemcategoryService.getAllSubCategories(categories);
            return itemRepository.findByItemCategoryIn(allCategories).stream().map(item -> {
                ItemMapper itemMapper = itemMapperFactory.getMapper(item);
                return itemMapper.toDto(item);
            }).toList();
        }
        catch (Exception e){
            return null;
        }
    }

    @Override
    public List<ItemDto> getAllItems() {
        return null;
    }
}
