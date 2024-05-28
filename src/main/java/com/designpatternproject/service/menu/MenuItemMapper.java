package com.designpatternproject.service.menu;

import com.designpatternproject.dto.menu.MenuItemDto;
import com.designpatternproject.entity.MenuItem;

public interface MenuItemMapper {
    MenuItemDto toDto(MenuItem menuItem);

}
