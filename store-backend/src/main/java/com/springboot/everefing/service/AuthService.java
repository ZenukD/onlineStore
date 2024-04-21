package com.springboot.everefing.service;

import com.springboot.everefing.dto.LoginDto;
import com.springboot.everefing.dto.RegisterDto;

public interface AuthService {
    String register(RegisterDto registerDto);

    LoginDto login(LoginDto loginDto);
}
