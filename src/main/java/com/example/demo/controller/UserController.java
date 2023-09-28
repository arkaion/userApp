package com.example.demo.controller;

import com.example.demo.model.UserDTO;
import com.example.demo.service.UserService;
import com.example.demo.utility.Exceptions.ResourceAlreadyCreatedException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<String> addUser(@Valid @RequestBody UserDTO userDTO) {

        String message = "";
        HttpStatus status = null;

        try {
            UserDTO savedUser = userService.addUser(userDTO);

            if (savedUser != null) {
                message = "User created successfully";
                status = HttpStatus.CREATED;
            } else {
                message = "Failed to create user";
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }

        } catch (ResourceAlreadyCreatedException e) {
            message = e.getMessage();
            status = HttpStatus.FORBIDDEN;
        }

        return new ResponseEntity<>(message, status);
    }


    @GetMapping("/")
    public ResponseEntity<?> getUserByUsername(@RequestParam String username) {
        UserDTO savedUser = userService.getUserByUsername(username);

        if (savedUser != null) {
            return new ResponseEntity<>(savedUser, HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

    }

}
