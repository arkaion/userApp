package com.example.demo.service.impl;

import com.example.demo.model.User;
import com.example.demo.model.UserDTO;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.utility.Exceptions.ResourceAlreadyCreatedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO addUser(UserDTO userDTO) {
        if(userRepository.findByUsername(userDTO.getUsername()).isPresent()){
            throw new ResourceAlreadyCreatedException("A user with the specified username already exists");
        }

        return userRepository.save(userDTO.userDtoToUser()).userToUserDto();
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        Optional<User> savedUserOptional = userRepository.findByUsername(username);

        return savedUserOptional.map(User::userToUserDto).orElse(null);
    }


}
