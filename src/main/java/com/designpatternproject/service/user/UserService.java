package com.designpatternproject.service.user;

import com.designpatternproject.dto.user.UserDto;

public interface UserService {
    public UserDto getUserDto(String userName) throws Exception;

    void updateUser(UserDto userDto) throws Exception;

    boolean checkCash(String userName, Long totalPrice);

    void pay(String userName, Long totalPrice);
}
