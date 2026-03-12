package com.moneymind.user.domain.model;

import com.moneymind.user.domain.valueObject.Email;
import jakarta.persistence.*;


import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "user_credentials")
public class Credentials {

    @Id
    @Column(name = "credential_id", nullable = false, updatable = false)
    private UUID id;

    @Embedded
    private Email email;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(name = "account_verified", nullable = false)
    private boolean emailVerified;

    @Column(name = "account_locked", nullable = false)
    private boolean accountLocked;

    @Column(name = "failed_login_attempts", nullable = false)
    private int failedLoginAttempts;

    @Column(name = "last_password_change")
    private LocalDateTime lastPasswordChange;

    private Credentials(Email email, String passwordHash){
        this.id = UUID.randomUUID();
        this.email = Objects.requireNonNull(email);
        this.passwordHash = Objects.requireNonNull(passwordHash);
        this.emailVerified = false;
        this.accountLocked = false;
        this.failedLoginAttempts = 0;
        this.lastPasswordChange = LocalDateTime.now();
    }

    protected Credentials(){}

    public static Credentials create(Email email, String passwordHash){
        Objects.requireNonNull(passwordHash, "Password hash cannot be null");

        return new Credentials(
                email,
                passwordHash
        );
    }

    public boolean isEmailVerified(){
        return emailVerified;
    }

    public boolean isAccountLocked(){
        return accountLocked;
    }

    public void changePassword(String newHash){
        this.passwordHash = Objects.requireNonNull(newHash);
        this.lastPasswordChange = LocalDateTime.now();
        this.failedLoginAttempts = 0;
    }

    public void markEmailAsVerified(){
        this.emailVerified = true;
    }

    public void registerFailedLogin(){
        failedLoginAttempts++;

        if(failedLoginAttempts >= 5){
            accountLocked = true;
        }
    }

    public void unlockAccount(){
        this.accountLocked = false;
        this.failedLoginAttempts = 0;
    }

    public boolean passwordMatches(String hash){
        return passwordHash.equals(hash);
    }

}