package com.designpatternproject.service.menu;

import com.designpatternproject.dto.menu.MenuItemDto;

import java.util.List;

public interface MenuService {
    List<MenuItemDto> getAllMenuItems();
}
