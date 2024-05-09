package com.designpatternproject.repository;

import com.designpatternproject.entity.ItemSubcategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemSubcategoryRepository extends JpaRepository<ItemSubcategory, Long> {
}