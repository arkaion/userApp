package com.example.demo.service;

import com.example.demo.model.UserDTO;

public interface UserService {

    UserDTO addUser(UserDTO userDTO);

    UserDTO getUserByUsername(String username);
}
