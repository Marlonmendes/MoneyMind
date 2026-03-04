package com.moneymind.user.domain.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "user_profiles")
@Access(AccessType.FIELD)
public class UserProfile {

    @Id
    private UUID id;

    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;

    private LocalDate birthDate;
    private String phone;
    private String timezone;
    private String language;

    public UserProfile(
            UUID id,
            String firstName,
            String lastName,
            LocalDate birthDate,
            String phone,
            String timezone,
            String language
    ) {
        this.id = Objects.requireNonNull(id);
        this.firstName = validate(firstName);
        this.lastName = validate(lastName);
        this.birthDate = birthDate;
        this.phone = phone;
        this.timezone = timezone;
        this.language = language;
    }

    public static UserProfile create(
            String firstName,
            String lastName,
            LocalDate birthDate,
            String phone,
            String timezone,
            String language
    ) {
        return new UserProfile(
                UUID.randomUUID(),
                firstName,
                lastName,
                birthDate,
                phone,
                timezone,
                language
        );
    }

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
