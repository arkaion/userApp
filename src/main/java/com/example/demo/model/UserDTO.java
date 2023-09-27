package com.example.demo.model;

import com.example.demo.utility.Enums.Gender;
import com.example.demo.utility.Validators.Adult;
import com.example.demo.utility.Validators.French;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;


import java.util.Date;

@Getter
@Setter
public class UserDTO {

    @NotBlank(message = "User must have a username.")
    private String username;

    @NotNull(message = "User must have a birthdate.")
    @Adult
    private Date birthdate;

    @NotBlank(message = "User must have a country.")
    @French
    private String country;

    private Gender gender;

    private String phoneNumber;

    public User userDtoToUser() {
        User user = new User();

        user.setPhoneNumber(this.getPhoneNumber());
        user.setCountry(this.getCountry());
        user.setGender(this.getGender());
        user.setBirthdate(this.getBirthdate());
        user.setUsername(this.getUsername());

        return user;
    }

}
