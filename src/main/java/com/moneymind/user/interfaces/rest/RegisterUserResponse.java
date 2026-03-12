package com.moneymind.user.interfaces.rest;

import java.util.UUID;

public class RegisterUserResponse {

    private UUID userId;

    public RegisterUserResponse(UUID userId){
        this.userId = userId;
    }

    public UUID getUserId(){
        return userId;
    }

}
