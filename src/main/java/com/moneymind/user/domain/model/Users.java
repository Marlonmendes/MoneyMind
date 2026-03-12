package com.moneymind.user.domain.model;

import com.moneymind.shared.domain.valueObject.Currency;
import com.moneymind.user.domain.valueObject.Email;
import com.moneymind.user.domain.valueObject.UserId;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "users")
@Access(AccessType.FIELD)
@Data
public class Users {

    @Getter
    @EmbeddedId
    private UserId id;

    @Embedded
    private Email email;

    @OneToOne(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "credentials_id")
    private Credentials credentials;

    @Embedded
    private UserProfile profile;

    @Embedded
    private FinancialSettings financialSettings; //Tipo de moeda, orçamento pessoal,

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    private boolean active;

    public FinancialSettings getFinancialSettings() {
        return financialSettings;
    }

    protected Users(){}

    public Users(
            UserId id,
            Email email,
            Credentials credentials,
            UserProfile profile,
            FinancialSettings financialSettings
    ) {
        this.id = Objects.requireNonNull(id);
        this.email = Objects.requireNonNull(email);
        this.profile = Objects.requireNonNull(profile);
        this.credentials = Objects.requireNonNull(credentials);
        this.financialSettings = Objects.requireNonNull(financialSettings);
        this.createdAt = LocalDateTime.now();
        this.active = true;
    }

    public static Users register(
            UserId id,
            Email email,
            Credentials credentials,
            UserProfile profile,
            FinancialSettings settings
    ) {
        return new Users(
                id,
                email,
                credentials,
                profile,
                settings
        );
    }

    public void changeEmail(Email newEmail){
        this.email = Objects.requireNonNull(newEmail);
    }

    public void updateProfile(
            String firstName,
            String lastName
    ) {
        profile.updateName(firstName, lastName);
    }

    public void ChangeCurrency(Currency currency){
        financialSettings.changeCurrency(currency);
    }

    public void changePassword(String newHashedPassword) {
        credentials.changePassword(newHashedPassword);
    }

    public void deactivate(){
        this.active = false;
    }

    public void activate(){
        this.active = true;
    }

    public boolean isActive(){
        return active;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof Users user)) return false;
        return id.equals(user.id);
    }

}
