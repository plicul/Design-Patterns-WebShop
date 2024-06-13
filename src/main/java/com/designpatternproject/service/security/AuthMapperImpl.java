package com.designpatternproject.service.security;

import com.designpatternproject.dto.menu.MenuItemDto;
import com.designpatternproject.dto.security.LoginDto;
import com.designpatternproject.entity.MenuItem;
import com.designpatternproject.entity.User;
import org.springframework.stereotype.Component;

@Component
public class AuthMapperImpl implements AuthMapper {

    @Override
    public User loginDtotoEntity(LoginDto loginDto) {
        if (loginDto == null) {
            return null;
        }
        User user = new User();
        user.setName(loginDto.getUsername());
        user.setPassword(loginDto.getPassword());

        return user;
    }
}