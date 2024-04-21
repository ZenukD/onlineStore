package com.springboot.everefing.controller;

import com.springboot.everefing.dto.LoginDto;
import com.springboot.everefing.dto.RegisterDto;
import com.springboot.everefing.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        String response = authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginDto> login(@RequestBody LoginDto loginDto){
        LoginDto response = authService.login(loginDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
