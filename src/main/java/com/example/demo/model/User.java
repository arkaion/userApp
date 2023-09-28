package com.example.demo.model;

import com.example.demo.utility.Enums.GenderEnum;
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

import java.text.SimpleDateFormat;
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
    private Date birthdate;

    @Column(nullable = false)
    @NotBlank
    private String country;

    private GenderEnum gender;

    private String phoneNumber;

    public UserDTO userToUserDto() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        UserDTO userDTO = new UserDTO();

        userDTO.setPhoneNumber(this.getPhoneNumber());
        userDTO.setCountry(this.getCountry());
        userDTO.setGender(this.getGender() == null ? "" : this.getGender().toString());
        userDTO.setBirthdate(sdf.format(this.getBirthdate()));
        userDTO.setUsername(this.getUsername());

        return userDTO;
    }

}
