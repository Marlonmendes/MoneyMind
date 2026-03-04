package com.moneymind.user.domain.model;

import com.moneymind.shared.domain.valueObject.Currency;
import com.moneymind.user.domain.model.UserProfile;
import com.moneymind.user.domain.valueObject.Email;
import com.moneymind.user.domain.valueObject.UserId;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "Users")
@Access(AccessType.FIELD)
public class Users {

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

    @OneToOne(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(name = "profile_id")
    private UserProfile profile;

    @Embedded
    private FinancialSettings financialSettings; //Tipo de moeda, orçamento pessoal,

    @Column(nullable = false, updatable = false)
    private Instant createdAt;
    private boolean active;

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
        this.createdAt = Instant.now();
        this.active = true;
    }

    public static Users register(
            UserId id,
            Email email,
            Credentials credentials,
            UserProfile profile,
            FinancialSettings financialSettings
    ) {
        return new Users(
                id,
                email,
                credentials,
                profile,
                financialSettings
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
