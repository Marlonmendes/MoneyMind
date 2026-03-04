package com.moneymind.user.domain.valueObject;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.util.Objects;
import java.util.UUID;

@Embeddable
@Access(AccessType.FIELD)
public class UserId {

    @Column(name = "user_id",nullable = false,updatable = false)
    private UUID value;

    protected UserId(){}

    public UserId(UUID value){
        this.value = value;
    }

    public static UserId newId(){
        return new UserId(UUID.randomUUID());
    }

    public static UserId from(UUID value){
        return new UserId(value);
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof UserId userId)) return false;
        return Objects.equals(value, userId.value);
    }

    @Override
    public int hashCode(){
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value.toString();
    }
}