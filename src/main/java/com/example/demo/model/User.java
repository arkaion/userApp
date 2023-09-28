package com.example.demo.model;

import com.example.demo.utility.Enums.Gender;
import com.example.demo.utility.Validators.Adult;
import com.example.demo.utility.Validators.French;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "\"user\"")
// table name specified with double quote to bypass the reserved "user" SQL keyword protection.
public class User {
    @Id
    @Column(unique = true, nullable = false)
    @NotBlank
    private String username;

    @Column(nullable = false)
    @NotNull
    @Past
    @Adult
    private Date birthdate;

    @Column(nullable = false)
    @NotBlank
    @French
    private String country;

    private Gender gender;

    private String phoneNumber;

    public UserDTO userToUserDto() {
        UserDTO userDTO = new UserDTO();

        userDTO.setPhoneNumber(this.getPhoneNumber());
        userDTO.setCountry(this.getCountry());
        userDTO.setGender(this.getGender());
        userDTO.setBirthdate(this.getBirthdate());
        userDTO.setUsername(this.getUsername());

        return userDTO;
    }

}
