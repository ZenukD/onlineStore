package com.springboot.everefing.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.springboot.everefing.dto.UserDto;
import com.springboot.everefing.entity.User;
import com.springboot.everefing.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserServiceImplTest {

    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;
    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        userRepository = mock(UserRepository.class);
        modelMapper = new ModelMapper();
        passwordEncoder = mock(PasswordEncoder.class);
        userService = new UserServiceImpl(userRepository, modelMapper, passwordEncoder);
    }

    @Test
    public void testUpdateUser_Success() {
        // Arrange
        String username = "testUser";
        UserDto userDto = new UserDto();
        userDto.setFirstName("John");
        userDto.setLastName("Doe");
        userDto.setAge(30);
        userDto.setEmail("john.doe@example.com");
        userDto.setPassword("newPassword");

        User user = new User();
        user.setUsername(username);
        user.setFirstName("Old");
        user.setLastName("Name");
        user.setAge(25);
        user.setEmail("old.email@example.com");
        user.setPassword("oldPassword");

        when(userRepository.findByUsername(username)).thenReturn(user);
        when(passwordEncoder.encode(userDto.getPassword())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        UserDto updatedUser = userService.updateUser(username, userDto);

        // Assert
        assertNotNull(updatedUser);
        assertEquals(userDto.getFirstName(), updatedUser.getFirstName());
        assertEquals(userDto.getLastName(), updatedUser.getLastName());
        assertEquals(userDto.getAge(), updatedUser.getAge());
        assertEquals(userDto.getEmail(), updatedUser.getEmail());
        // Verify that password is encoded before saving
        verify(passwordEncoder).encode(userDto.getPassword());
    }


}
