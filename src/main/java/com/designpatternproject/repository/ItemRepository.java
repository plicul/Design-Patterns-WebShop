package com.designpatternproject.repository;

import com.designpatternproject.entity.Item;
import com.designpatternproject.entity.ItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByItemCategoryIn(Collection<ItemCategory> itemCategories);
}