package com.designpatternproject.service.security;

import com.designpatternproject.dto.security.LoginDto;

public interface AuthService {
    String login(LoginDto loginDto);

    void register(LoginDto loginDto) throws Exception;
}