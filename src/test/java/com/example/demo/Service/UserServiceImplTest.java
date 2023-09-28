package com.example.demo.Service;

import com.example.demo.model.User;
import com.example.demo.model.UserDTO;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.impl.UserServiceImpl;
import com.example.demo.utility.Exceptions.ResourceAlreadyCreatedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddUserCaseUserOK() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("testUser");
        userDTO.setBirthdate("2000-01-01");
        userDTO.setCountry("France");

        User user = new User();
        user.setUsername("testUser");
        user.setBirthdate(formatter.parse("2000-01-01"));
        user.setCountry("France");

        when(userRepository.findByUsername("testUser")).thenReturn(Optional.empty());
        when(userRepository.save(any())).thenReturn(user);

        UserDTO savedUser = userService.addUser(userDTO);

        assertNotNull(savedUser);
        assertEquals(user.getUsername(), savedUser.getUsername());
        assertEquals(user.getBirthdate(), formatter.parse(savedUser.getBirthdate()));
        assertEquals(user.getCountry(), savedUser.getCountry());
    }

    @Test
    public void testAddUserCaseResourceAlreadyCreatedException() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("testUser");

        when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(new User()));

        assertThrows(ResourceAlreadyCreatedException.class, () -> {
            userService.addUser(userDTO);
        });
    }

    @Test
    public void testGetUserByUsernameCaseUsernameNull() {
        assertNull(userService.getUserByUsername(null));
    }

    @Test
    public void testGetUserByUsernameCaseUserNotFound() {
        when(userRepository.findByUsername("testUser")).thenReturn(Optional.empty());
        assertNull(userService.getUserByUsername("testUser"));
    }

    @Test
    public void testGetUserByUsernameCaseUserFound() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        User user = new User();
        user.setUsername("testUser");
        user.setBirthdate(formatter.parse("2000-01-01"));
        user.setCountry("France");

        when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(user));
        assertNotNull(userService.getUserByUsername("testUser"));
    }


}