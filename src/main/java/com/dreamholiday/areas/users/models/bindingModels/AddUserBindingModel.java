package com.dreamholiday.areas.users.models.bindingModels;


import com.dreamholiday.areas.users.annotations.IsPasswordsMatching;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@IsPasswordsMatching
public class AddUserBindingModel {

    @NotBlank(message = "First name required")
    @Size(min = 2, message = "First name too short(min 2 symbols required)")
    private String firstName;

    @NotBlank(message = "Last name required")
    @Size(min = 2, message = "Last name too short(min 2 symbols required)")
    private String lastName;

    @NotBlank(message = "Gender not selected")
    private String gender;

    @NotBlank(message = "Email or username required")
    @Pattern(regexp = "^[\\w+]{6,}$|^.+@.+\\.[a-zA-Z]{2}$", message = "Invalid email or username")
    private String username;

    @NotBlank(message = "Password missing")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$", message = "Invalid password, minimum 8 characters, at least 1 alphabet, 1 number and 1 special character required")
    private String password;

    private String confirmPassword;

    @NotBlank(message = "Phone number required")
    @Pattern(regexp = "^\\+?(?:[0-9] ?){6,14}[0-9]$", message = "Invalid phone")
    private String phone;

    @NotBlank(message = "Country not selected")
    private String country;

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String email) {
        this.username = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return this.confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
