package com.springboot.everefing.service.impl;

import com.springboot.everefing.dto.LoginDto;
import com.springboot.everefing.dto.RegisterDto;
import com.springboot.everefing.entity.Role;
import com.springboot.everefing.entity.User;
import com.springboot.everefing.exception.ProductAPIException;
import com.springboot.everefing.repository.RoleRepository;
import com.springboot.everefing.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AuthServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthServiceImpl authService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegister_Success() {
        RegisterDto registerDto = new RegisterDto();
        registerDto.setUsername("testuser");
        registerDto.setEmail("test@example.com");
        registerDto.setPassword("password");

        Role role = new Role();
        role.setName("ROLE_USER");
        when(roleRepository.findByName("ROLE_USER")).thenReturn(role);

        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setUsername(registerDto.getUsername());
        savedUser.setEmail(registerDto.getEmail());
        savedUser.setPassword("encodedPassword");
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        savedUser.setRoles(roles);
        when(userRepository.existsByUsername(registerDto.getUsername())).thenReturn(false);
        when(userRepository.existsByEmail(registerDto.getEmail())).thenReturn(false);
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        String response = authService.register(registerDto);

        assertEquals("User registered successfully!", response);
    }

    @Test
    public void testRegister_UsernameExists() {
        RegisterDto registerDto = new RegisterDto();
        registerDto.setUsername("testuser");
        registerDto.setEmail("test@example.com");
        registerDto.setPassword("password");
        when(userRepository.existsByUsername(registerDto.getUsername())).thenReturn(true);

        assertThrows(ProductAPIException.class, () -> authService.register(registerDto));
    }

    @Test
    public void testRegister_EmailExists() {
        RegisterDto registerDto = new RegisterDto();
        registerDto.setUsername("testuser");
        registerDto.setEmail("test@example.com");
        registerDto.setPassword("password");
        when(userRepository.existsByUsername(registerDto.getUsername())).thenReturn(false);
        when(userRepository.existsByEmail(registerDto.getEmail())).thenReturn(true);

        assertThrows(ProductAPIException.class, () -> authService.register(registerDto));
    }

    @Test
    public void testLogin_Success() {
        LoginDto loginDto = new LoginDto();
        loginDto.setUsernameOrEmail("testuser");
        loginDto.setPassword("password");

        Authentication authentication = mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = new User();
        Role role = new Role();
        role.setName("ROLE_USER");
        user.setRoles(Set.of(role));
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
        when(userRepository.findByUsernameOrEmail(loginDto.getUsernameOrEmail(), loginDto.getUsernameOrEmail())).thenReturn(Optional.of(user));

        LoginDto response = authService.login(loginDto);

        assertEquals("ROLE_USER", response.getRole());
    }
}
