package com.moneymind.user.interfaces.rest;

public class RegisterUserRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String timezone;
    private String language;

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getTimezone() {
        return timezone;
    }

    public String getLanguage(){
        return language;
    }
}
