package com.moneymind.user.domain.valueObject;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.util.Objects;
import java.util.regex.Pattern;

@Embeddable
public class Email {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

    @Getter
    @Column(name = "email", nullable = false, unique = true)
    private String userEmail;

    public Email(String userEmail) {
        validade(userEmail);
        this.userEmail = normalize(userEmail);
    }

    private void validade(String email){
        if(email == null || email.isBlank()){
            throw new IllegalArgumentException("Email cannot be empty");
        }
        if(!EMAIL_PATTERN.matcher(email).matches()){
            throw new IllegalArgumentException("Invalid email format");
        }
    }

    protected Email(){}

    private String normalize(String email){
        return email.trim().toLowerCase();
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof Email that)) return false;
        return userEmail.equals(that.userEmail);
    }

    @Override
    public int hashCode(){
        return Objects.hash(userEmail);
    }

    @Override
    public String toString(){
        return userEmail;
    }
}
