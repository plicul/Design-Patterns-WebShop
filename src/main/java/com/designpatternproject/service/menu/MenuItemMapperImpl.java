package com.designpatternproject.service.menu;

import com.designpatternproject.dto.menu.MenuItemDto;
import com.designpatternproject.entity.MenuItem;
import org.springframework.stereotype.Component;

@Component
public class MenuItemMapperImpl implements MenuItemMapper {
    @Override
    public MenuItemDto toDto(MenuItem menuItem) {
        if (menuItem == null) {
            return null;
        }
        MenuItemDto menuItemDto = new MenuItemDto(
                menuItem.getName(),
                menuItem.getRoutePath()
        );
        return menuItemDto;
    }
}