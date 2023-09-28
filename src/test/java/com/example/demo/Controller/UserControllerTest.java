package com.example.demo.Controller;

import com.example.demo.controller.UserController;
import com.example.demo.model.UserDTO;
import com.example.demo.service.UserService;
import com.example.demo.utility.Exceptions.ResourceAlreadyCreatedException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    private AutoCloseable closeable;
    UserDTO userDTO;


    @BeforeEach
    public void setup() {
        closeable = MockitoAnnotations.openMocks(this);
        userDTO = new UserDTO();
        userDTO.setUsername("testUser");
    }

    @AfterEach
    void closeService() throws Exception {
        closeable.close();
    }

    @Test
    public void testAddUserCaseSuccessful() {
        when(userService.addUser(userDTO)).thenReturn(userDTO);

        ResponseEntity<String> response = userController.addUser(userDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testAddUserCaseFailure() {
        when(userService.addUser(userDTO)).thenReturn(null);

        ResponseEntity<String> response = userController.addUser(userDTO);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testAddUserCaseResourceAlreadyCreatedException() {
        when(userService.addUser(userDTO)).thenThrow(new ResourceAlreadyCreatedException("User already exists"));

        ResponseEntity<String> response = userController.addUser(userDTO);

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

}
