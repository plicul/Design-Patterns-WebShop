package com.designpatternproject.repository;

import com.designpatternproject.entity.Item;
import com.designpatternproject.entity.ItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    //@Query(value = "select item from Item item where item.itemCategory.id in :categories")
    List<Item> findByItemCategoryIn(@Param("categories") Collection<ItemCategory> itemCategories);
}