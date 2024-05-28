package com.designpatternproject.service.security;

import com.designpatternproject.dto.menu.MenuItemDto;
import com.designpatternproject.dto.security.LoginDto;
import com.designpatternproject.entity.MenuItem;
import com.designpatternproject.entity.User;

public interface AuthMapper {
    User loginDtotoEntity(LoginDto loginDto);

}
