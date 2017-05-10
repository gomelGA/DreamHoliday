package com.dreamholiday.areas.users.models.bindingModels;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class EditUserBindingModel {

    private Long id;

    @NotBlank(message = "First name required")
    @Size(min = 2, message = "First name too short(min 2 symbols required)")
    private String firstName;

    @NotBlank(message = "Last name required")
    @Size(min = 2, message = "Last name too short(min 2 symbols required)")
    private String lastName;

    @NotBlank(message = "Phone number required")
    @Pattern(regexp = "^\\+?(?:[0-9] ?){6,14}[0-9]$", message = "Invalid phone")
    private String phone;

    @NotBlank(message = "Email or username required")
    @Pattern(regexp = "^[\\w+]{6,}$|^.+@.+\\.[a-zA-Z]{2}$", message = "Invalid email or username")
    private String username;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
