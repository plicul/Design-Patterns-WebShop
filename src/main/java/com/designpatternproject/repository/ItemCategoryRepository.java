package com.designpatternproject.repository;

import com.designpatternproject.entity.ItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ItemCategoryRepository extends JpaRepository<ItemCategory, Long> {
    Optional<ItemCategory> findByCategoryIgnoreCase(String category);
    List<ItemCategory> findByParent(Long parent);
    List<ItemCategory> findByCategoryIn(Collection<String> categories);
    List<ItemCategory> findByCategory(String category);
}