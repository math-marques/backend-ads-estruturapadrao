package com.bambu.backend.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(){
        super("Usuário não existe!");
    }
}