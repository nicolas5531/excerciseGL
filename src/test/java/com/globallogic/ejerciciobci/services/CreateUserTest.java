package com.globallogic.ejerciciobci.services;

import com.globallogic.ejerciciobci.controllers.dto.PhoneDTO;
import com.globallogic.ejerciciobci.controllers.dto.UserDTO;
import com.globallogic.ejerciciobci.controllers.exceptions.ExistentUserFoundException;
import com.globallogic.ejerciciobci.repositories.IUserRepository;
import com.globallogic.ejerciciobci.repositories.entities.User;
import com.globallogic.ejerciciobci.services.util.JWTUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateUserTest {

    @Mock
    private IUserRepository userRepository;

    @Mock
    private JWTUtil jwtUtil;

    @InjectMocks
    private CreateUser createUser;

    @Test
    void testCreateUser() {
        // Arrange
        UserDTO userDTO = UserDTO.builder()
                .name("name")
                .email("email@email.com")
                .password("a2asfGfdfdf4")
                .phones(new HashSet<>(Arrays.asList(PhoneDTO.builder()
                        .number(543764001122L)
                        .cityCode(33)
                        .countryCode("54")
                        .build())))
                .build();
        User user = new User(userDTO);
        when(userRepository.findByEmail(userDTO.getEmail())).thenReturn(null);
        when(jwtUtil.createTokenWithShortExpiration(any())).thenReturn("mockedToken");
        when(userRepository.save(any())).thenReturn(user);

        // Act
        User createdUser = createUser.create(userDTO);

        // Assert
        assertNotNull(createdUser);
        verify(userRepository, times(1)).findByEmail(userDTO.getEmail());
        verify(jwtUtil, times(1)).createTokenWithShortExpiration(any());
        verify(userRepository, times(1)).save(any());
    }

    @Test
    void testCreateUserWithExistingUser() {
        // Arrange
        UserDTO userDTO = UserDTO.builder()
                .name("name")
                .email("email@email.com")
                .password("a2asfGfdfdf4")
                .phones(new HashSet<PhoneDTO>(Arrays.asList(PhoneDTO.builder()
                        .number(543764001122L)
                        .cityCode(33)
                        .countryCode("54")
                        .build())))
                .build();
        User existingUser = new User(userDTO);
        when(userRepository.findByEmail(userDTO.getEmail())).thenReturn(existingUser);

        // Act and Assert
        assertThrows(ExistentUserFoundException.class, () -> createUser.create(userDTO));
        verify(userRepository, times(1)).findByEmail(userDTO.getEmail());
        verify(jwtUtil, never()).createTokenWithShortExpiration(any());
        verify(userRepository, never()).save(any());
    }
}
