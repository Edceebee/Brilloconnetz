package com.brilloconnetz.javaTest.pojo;

import com.brilloconnetz.javaTest.customValidation.DateOfBirthValidation;
import com.brilloconnetz.javaTest.customValidation.PasswordValidation;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NoArgsConstructor;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class JwtGeneration {

    @NotNull(message = "Username cannot be null")
    @NotEmpty(message = "Username cannot be empty")
    @Size(min = 4, message = "Username must have minimum of 4 characters")
    private String username;

    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Invalid email address")
    private String email;

    @NotEmpty(message = "Password cannot be empty")
    @PasswordValidation(message = "strong password with at least 1 upper case, 1 special character, 1 number and must be minimum of 8 characters")
    private String password;

    @NotEmpty(message = "Date of Birth cannot be empty")
    @DateOfBirthValidation(message = "should be 16 years or greater")
    private String dateOfBirth;
}
