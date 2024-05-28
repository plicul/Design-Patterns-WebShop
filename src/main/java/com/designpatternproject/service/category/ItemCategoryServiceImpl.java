package com.designpatternproject.service.category;

import com.designpatternproject.dto.itemCategory.CategoryComponentDto;
import com.designpatternproject.dto.itemCategory.CategoryCompositeDto;
import com.designpatternproject.dto.itemCategory.CategoryLeafDto;
import com.designpatternproject.entity.ItemCategory;
import com.designpatternproject.repository.ItemCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemCategoryServiceImpl implements ItemCategoryService {

    @Autowired
    private ItemCategoryRepository itemCategoryRepository;
    @Cacheable("categories")
    @Override
    public List<CategoryComponentDto> getCategoryTree() {
        List<ItemCategory> categories = itemCategoryRepository.findAll();
        return buildCategoryTree(categories);
    }

    private List<CategoryComponentDto> buildCategoryTree(List<ItemCategory> categories) {
        Map<Long, CategoryComponentDto> categoryMap = new HashMap<>();
        List<CategoryComponentDto> roots = new ArrayList<>();

        // Create DTOs and populate the map
        for (ItemCategory category : categories) {
            CategoryComponentDto dto;
            if (hasChildren(category, categories)) {
                dto = new CategoryCompositeDto(category.getId(), category.getCategory(), category.getLevel(), category.getParent());
            } else {
                dto = new CategoryLeafDto(category.getId(), category.getCategory(), category.getLevel(), category.getParent());
            }
            categoryMap.put(dto.getId(), dto);

            if (category.getParent() == null) {
                roots.add(dto);
            }
        }

        // Build the tree structure
        for (ItemCategory category : categories) {
            if (category.getParent() != null) {
                CategoryCompositeDto parentDto = (CategoryCompositeDto) categoryMap.get(category.getParent());
                CategoryComponentDto childDto = categoryMap.get(category.getId());
                parentDto.getSubcategories().add(childDto);
            }
        }

        return roots;
    }

    private boolean hasChildren(ItemCategory category, List<ItemCategory> categories) {
        return categories.stream().anyMatch(c -> c.getParent() != null && c.getParent().equals(category.getId()));
    }
    /*@Cacheable(cacheNames = "categoryTree", cacheManager = "defaultCacheManager")
    public List<CategoryDto> getCategoryTree() {
        List<ItemCategory> categories = itemCategoryRepository.findAll();
        return buildCategoryTree(categories);
    }
    @CacheEvict(value = "categoryTree", allEntries = true)
    @Scheduled(fixedRateString = "${caching.spring.TTL}")
    public void emptyCategoryTreeCache(){
        Logger.getInstance().log(LogLevel.INFO,"Category Tree Emptied!", "");
    }

    private List<CategoryDto> buildCategoryTree(List<ItemCategory> categories) {
        Map<Long, CategoryDto> categoryMap = new HashMap<>();
        List<CategoryDto> roots = new ArrayList<>();

        // Create DTOs and populate the map
        for (ItemCategory category : categories) {
            CategoryDto dto = new CategoryDto(
                    category.getId(),
                    category.getCategory(),
                    category.getLevel(),
                    category.getParent()
            );
            categoryMap.put(dto.getId(), dto);

            if (category.getParent() == null) {
                roots.add(dto);
            }
        }

        // Build the tree structure
        for (ItemCategory category : categories) {
            if (category.getParent() != null) {
                CategoryDto parentDto = categoryMap.get(Long.valueOf(category.getParent()));
                CategoryDto childDto = categoryMap.get(category.getId());
                parentDto.getSubcategories().add(childDto);
            }
        }

        return roots;
    }

     */
}
