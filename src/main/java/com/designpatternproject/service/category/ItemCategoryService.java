package com.designpatternproject.service.category;

import com.designpatternproject.dto.itemCategory.CategoryComponentDto;
import com.designpatternproject.entity.ItemCategory;

import java.util.List;

public interface ItemCategoryService {
    public List<CategoryComponentDto> getCategoryTree();
}
