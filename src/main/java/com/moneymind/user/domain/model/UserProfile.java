package com.moneymind.user.domain.model;

import com.moneymind.user.domain.valueObject.UserId;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class UserProfile {

    @Column(insertable=false, updatable=false)
    private UserId id;
    @Column(name = "firstName",nullable = false)
    private String firstName;
    @Column(name = "lastName",nullable = false)
    private String lastName;
    @Column(name = "birthDate")
    private LocalDate birthDate;
    @Column(name = "phone")
    private String phone;
    @Column(name = "timezone",nullable = false)
    private String timezone;
    @Column(name = "language",nullable = false)
    private String language;

    public UserProfile(
            UserId id,
            String firstName,
            String lastName,
            String timezone,
            String language
    ) {
        this.id = Objects.requireNonNull(id);
        this.firstName = validate(firstName);
        this.lastName = validate(lastName);
        this.timezone = timezone;
        this.language = language;
    }

    public static UserProfile create(
            UserId id,
            String firstName,
            String lastName,
            String timezone,
            String language
    ) {
        return new UserProfile(
                id,
                firstName,
                lastName,
                timezone,
                language
        );
    }

    protected UserProfile(){}

    private String validate(String value){
        if(value == null || value.isBlank()){
            throw new IllegalArgumentException("Invalid profile value");
        }
        return value;
    }

    public void updateName(String firstName, String lastName){
        this.firstName = validate(firstName);
        this.lastName = validate(lastName);
    }

    public void updatePhone(String phone){
        this.phone = phone;
    }

    public void changeLanguage(String language){
        this.language = language;
    }

    public void changeTimezone(String timezone){
        this.timezone = timezone;
    }

    public String getFullName(){
        return firstName + " " + lastName;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof UserProfile that))return false;
        return Objects.equals(firstName, that.firstName)
                && Objects.equals(lastName, that.lastName)
                && Objects.equals(birthDate, that.birthDate);
    }

    @Override
    public int hashCode(){
        return Objects.hash(firstName, lastName, birthDate);
    }

}
