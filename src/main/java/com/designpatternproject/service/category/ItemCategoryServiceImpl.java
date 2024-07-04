package com.designpatternproject.service.category;

import com.designpatternproject.dto.itemCategory.CategoryComponentDto;
import com.designpatternproject.dto.itemCategory.CategoryCompositeDto;
import com.designpatternproject.dto.itemCategory.CategoryLeafDto;
import com.designpatternproject.entity.ItemCategory;
import com.designpatternproject.repository.ItemCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Component
public class ItemCategoryServiceImpl implements ItemCategoryService {

    @Autowired
    private ItemCategoryRepository itemCategoryRepository;
    @Cacheable("categories")
    @Override
    public List<CategoryComponentDto> getCategoryTree() {
        List<ItemCategory> categories = itemCategoryRepository.findAll();
        return buildCategoryTree(categories);
    }

    @Override
    public List<ItemCategory> getAllSubCategories(List<String> categories) {
        List<ItemCategory> itemCategories = itemCategoryRepository.findByCategoryIn(categories);
        Set<ItemCategory> subCategories = new HashSet<>(itemCategories);

        for (ItemCategory category : itemCategories) {
            getSubCategoriesRecursive(category, subCategories);
        }

        return new ArrayList<>(subCategories);
    }

    @Override
    public List<String> getAll() {
        List<ItemCategory> itemCategories = itemCategoryRepository.findAll();
        return itemCategories.stream().map(ItemCategory::getCategory).toList();
    }

    private void getSubCategoriesRecursive(ItemCategory category, Set<ItemCategory> subCategoryNames) {
        List<ItemCategory> subCategories = itemCategoryRepository.findByParent(category.getId());

        for (ItemCategory subCategory : subCategories) {
            if (subCategoryNames.add(subCategory)) {
                getSubCategoriesRecursive(subCategory, subCategoryNames);
            }
        }
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

        for (ItemCategory category : categories) {
            if (category.getParent() != null) {
                Long parentId = category.getParent();
                CategoryComponentDto parentDto = categoryMap.get(parentId);
                CategoryComponentDto childDto = categoryMap.get(category.getId());

                if (parentDto instanceof CategoryCompositeDto) {
                    ((CategoryCompositeDto) parentDto).getSubcategories().add(childDto);
                } else {
                    CategoryCompositeDto compositeParentDto = new CategoryCompositeDto(
                            parentDto.getId(), parentDto.getCategory(), parentDto.getLevel(), parentDto.getParent());
                    compositeParentDto.getSubcategories().add(childDto);
                    categoryMap.put(compositeParentDto.getId(), compositeParentDto);
                    if (roots.contains(parentDto)) {
                        roots.remove(parentDto);
                        roots.add(compositeParentDto);
                    }
                }
            }
        }


        return roots;
    }

    private boolean hasChildren(ItemCategory category, List<ItemCategory> categories) {
        return categories.stream().anyMatch(c -> c.getParent() != null && c.getParent().equals(category.getId()));
    }

}
