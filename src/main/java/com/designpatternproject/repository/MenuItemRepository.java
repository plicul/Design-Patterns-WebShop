package com.designpatternproject.repository;

import com.designpatternproject.dto.menu.MenuItemDto;
import com.designpatternproject.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuItemRepository extends JpaRepository<MenuItem, Integer> {
}