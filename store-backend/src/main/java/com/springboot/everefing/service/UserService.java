package com.springboot.everefing.service;

import com.springboot.everefing.dto.UserDto;
import com.springboot.everefing.entity.User;

public interface UserService {

    UserDto updateUser(String username, UserDto userDto);
}
