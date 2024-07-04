package com.designpatternproject.service.item;

import com.designpatternproject.dto.item.ItemDto;
import com.designpatternproject.entity.Item;
import com.designpatternproject.entity.ItemCategory;
import com.designpatternproject.entity.Price;
import com.designpatternproject.repository.ItemCategoryRepository;
import com.designpatternproject.repository.ItemRepository;
import com.designpatternproject.repository.PriceRepository;
import com.designpatternproject.repository.PriceTypeRepository;
import com.designpatternproject.service.category.ItemCategoryServiceImpl;
import com.designpatternproject.service.item.mapper.ItemMapper;
import com.designpatternproject.service.item.mapper.ItemMapperFactory;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ItemServiceImpl implements ItemService{

    private final ItemRepository itemRepository;
    private final ItemMapperFactory itemMapperFactory;
    private final ItemCategoryServiceImpl itemcategoryService;
    private final PriceTypeRepository priceTypeRepository;
    private final PriceRepository priceRepository;

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
            //List<Item> items = itemRepository.findByItemCategoryIn(allCategories.stream().map(ItemCategory::getId).toList());
            List<ItemDto> itemDtos = itemRepository.findByItemCategoryIn(allCategories).stream().map(item -> {
                ItemMapper itemMapper = itemMapperFactory.getMapper(item);
                try {
                    return itemMapper.toDto(item);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }).toList();
            return itemDtos;
        }
        catch (Exception e){
            return null;
        }
    }

    @Override
    public List<ItemDto> getAllItems() {
        try {
            List<Item> items = itemRepository.findAll();

            return items.stream().map(item -> {
                ItemMapper itemMapper = itemMapperFactory.getMapper(item);
                try {
                    return itemMapper.toDto(item);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }).toList();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Boolean saveItems(List<ItemDto> itemDtos) {
        try {
            List<Item> items = itemDtos.stream().map(itemDto -> {
                ItemMapper itemMapper = itemMapperFactory.getMapper(itemDto);
                try {
                    return itemMapper.toEntity(itemDto);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }).toList();
            itemRepository.saveAll(items);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean deleteItems(List<Long> itemIds) {
        try {
            itemIds.forEach(priceRepository::deleteByItem_Id);
            itemRepository.deleteAllById(itemIds);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public Boolean saveNewItem(ItemDto itemDto) {
        try {
            ItemMapper itemMapper = itemMapperFactory.getMapper(itemDto);
            try {
                Item newItem = itemMapper.toEntity(itemDto);
                newItem = itemRepository.saveAndFlush(newItem);
                Price newPrice = new Price();
                newPrice.setItem(newItem);
                newPrice.setAmount(0L);
                newPrice.setPriceType(priceTypeRepository.findById(1001L).orElseThrow());
                newPrice.setStartDate(new Date());
                newPrice.setCreationDate(new Date());
                newPrice.setActiveFlag("true");
                newPrice = priceRepository.saveAndFlush(newPrice);
                List<Price> prices = new ArrayList<>();
                prices.add(newPrice);
                newItem.setPrices(new HashSet<>(prices));
                itemRepository.save(newItem);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean checkItemAvailability(Long itemId, Integer amount) {
        Item item = itemRepository.findById(itemId).orElseThrow();
        return item.getQuantity() >= amount;
    }

    @Override
    public void sellItem(Long itemId, Integer amount) {
        Item item = itemRepository.findById(itemId).orElseThrow();
        item.setQuantity(item.getQuantity() - amount);
        itemRepository.save(item);
    }
}
