package com.moneymind.user.domain.valueObject;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.util.Objects;
import java.util.UUID;

@Embeddable
@Data
public class UserId {

    @Column(insertable=false, updatable=false)
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