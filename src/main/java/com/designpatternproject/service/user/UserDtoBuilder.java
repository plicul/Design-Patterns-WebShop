package com.designpatternproject.service.user;

import com.designpatternproject.dto.user.AddressDto;
import com.designpatternproject.dto.user.ContactDto;
import com.designpatternproject.dto.user.UserDto;
import com.designpatternproject.dto.user.UserTypeDto;
import com.designpatternproject.entity.UserType;

import java.util.List;

public class UserDtoBuilder {
    private UserDto userDto;

    public UserDtoBuilder() {
        this.userDto = new UserDto();
    }

    public UserDtoBuilder withId(Long id) {
        this.userDto.setId(id);
        return this;
    }

    public UserDtoBuilder withName(String name) {
        this.userDto.setName(name);
        return this;
    }

    public UserDtoBuilder withPassword(String password) {
        this.userDto.setPassword(password);
        return this;
    }

    public UserDtoBuilder withType(UserTypeDto type) {
        this.userDto.setType(type);
        return this;
    }

    public UserDtoBuilder withContact(ContactDto contact) {
        this.userDto.setContact(contact);
        return this;
    }

    public UserDtoBuilder withAddress(AddressDto address) {
        this.userDto.setAddress(address);
        return this;
    }

    public UserDto build() {
        return this.userDto;
    }
}
