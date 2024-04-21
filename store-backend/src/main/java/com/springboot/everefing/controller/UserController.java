package com.springboot.everefing.controller;


import com.springboot.everefing.dto.UserDto;
import com.springboot.everefing.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("api/user")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @PatchMapping("{username}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("username") String username,
                                              @RequestBody UserDto userDto) {
        UserDto updateUser = userService.updateUser(username, userDto);
        return ResponseEntity.ok(updateUser);
    }
    
}
