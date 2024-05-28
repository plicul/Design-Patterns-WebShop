package com.designpatternproject.service.menu;

import com.designpatternproject.dto.menu.MenuItemDto;
import com.designpatternproject.entity.MenuItem;
import com.designpatternproject.repository.MenuItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class MenuServiceImpl implements MenuService{

    private final MenuItemRepository menuItemRepository;
    private final MenuItemMapperImpl menuItemMapper;

    public MenuServiceImpl(MenuItemRepository menuItemRepository, MenuItemMapperImpl menuItemMapper) {
        this.menuItemRepository = menuItemRepository;
        this.menuItemMapper = menuItemMapper;
    }

    @Override
    public List<MenuItemDto> getAllMenuItems() {
        List<MenuItem> menuItems = menuItemRepository.findAll();
        return menuItems.stream().map(menuItemMapper::toDto).toList();
    }
}
