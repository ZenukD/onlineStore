package com.springboot.everefing.controller;

import com.springboot.everefing.dto.LoginDto;
import com.springboot.everefing.dto.RegisterDto;
import com.springboot.everefing.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class AuthControllerTest {

    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegister() {
        RegisterDto registerDto = new RegisterDto();
        String response = "Registration successful";
        when(authService.register(any(RegisterDto.class))).thenReturn(response);

        ResponseEntity<String> actualResponse = authController.register(registerDto);

        assertEquals(HttpStatus.CREATED, actualResponse.getStatusCode());
        assertEquals(response, actualResponse.getBody());
    }

    @Test
    public void testLogin() {
        LoginDto loginDto = new LoginDto();
        LoginDto response = new LoginDto();

        when(authService.login(any(LoginDto.class))).thenReturn(response);

        ResponseEntity<LoginDto> actualResponse = authController.login(loginDto);

        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        assertEquals(response, actualResponse.getBody());
    }

}