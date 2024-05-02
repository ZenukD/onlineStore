package com.springboot.everefing.entity;

import static org.junit.jupiter.api.Assertions.*;

import com.springboot.everefing.entity.Role;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RoleTest {

    @Test
    void testConstructor() {
        // Arrange
        Long id = 1L;
        String name = "ROLE_USER";

        // Act
        Role role = new Role(id, name);

        // Assert
        assertNotNull(role);
        assertEquals(id, role.getId());
        assertEquals(name, role.getName());
    }

    @Test
    void testSettersAndGetters() {
        // Arrange
        Role role = new Role();
        Long id = 1L;
        String name = "ROLE_USER";

        // Act
        role.setId(id);
        role.setName(name);

        // Assert
        assertEquals(id, role.getId());
        assertEquals(name, role.getName());
    }
}
