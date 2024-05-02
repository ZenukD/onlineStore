package com.springboot.everefing.security;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.springboot.everefing.entity.Role;
import com.springboot.everefing.entity.User;
import com.springboot.everefing.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;
import java.util.Optional;

class CustomUserDetailsServiceTest {

    @Test
    void loadUserByUsername_WhenUserExists_ReturnsUserDetails() {
        // Arrange
        String usernameOrEmail = "testUser";
        User mockUser = new User();
        mockUser.setUsername(usernameOrEmail);
        mockUser.setPassword("testPassword");
        Role mockRole = new Role();
        mockRole.setName("ROLE_USER");
        mockUser.setRoles(Collections.singleton(mockRole));

        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail))
                .thenReturn(Optional.of(mockUser));

        CustomUserDetailsService userDetailsService = new CustomUserDetailsService(userRepository);

        // Act
        UserDetails userDetails = userDetailsService.loadUserByUsername(usernameOrEmail);

        // Assert
        assertNotNull(userDetails);
        assertEquals(usernameOrEmail, userDetails.getUsername());
        assertEquals(mockUser.getPassword(), userDetails.getPassword());
        assertEquals(1, userDetails.getAuthorities().size());
        assertTrue(userDetails.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("ROLE_USER")));
    }

    @Test
    void loadUserByUsername_WhenUserNotExists_ThrowsUsernameNotFoundException() {
        // Arrange
        String nonExistingUsername = "nonExistingUser";

        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findByUsernameOrEmail(nonExistingUsername, nonExistingUsername))
                .thenReturn(Optional.empty());
        // Act
        CustomUserDetailsService userDetailsService = new CustomUserDetailsService(userRepository);

        // Act & Assert
        assertThrows(UsernameNotFoundException.class, () -> userDetailsService.loadUserByUsername(nonExistingUsername));
    }
}
