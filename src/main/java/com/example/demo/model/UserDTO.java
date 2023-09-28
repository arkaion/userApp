package com.example.demo.model;

import com.example.demo.utility.Enums.GenderEnum;
import com.example.demo.utility.Validators.Adult;
import com.example.demo.utility.Validators.DateFormat;
import com.example.demo.utility.Validators.French;
import com.example.demo.utility.Validators.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

@Getter
@Setter
public class UserDTO {

    @NotBlank(message = "User must have a username.")
    private String username;

    @NotBlank(message = "User must have a birthdate.")
    @DateFormat
    @Adult
    private String birthdate;

    @NotBlank(message = "User must have a country.")
    @French
    private String country;

    @Gender
    private String gender;

    // regex from https://ihateregex.io/expr/phone/
    // Modified to accept empty strings as well, as a user may not have a phone number.
    @Pattern(regexp = "^$|^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$", flags = Pattern.Flag.UNICODE_CASE, message = "User phone number must be valid")
    private String phoneNumber;

    public User userDtoToUser() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        User user = new User();

        user.setUsername(this.getUsername());
        user.setPhoneNumber(this.getPhoneNumber());
        user.setCountry(this.getCountry());
        user.setGender(this.getGender() == null ? null : GenderEnum.valueOf(this.getGender()));
        try {
            user.setBirthdate(formatter.parse(this.getBirthdate()));
        } catch (ParseException e) {
            // Exception already handled by the @Date validator.
        }

        return user;
    }

}
