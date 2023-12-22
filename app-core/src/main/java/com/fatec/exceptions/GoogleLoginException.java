package com.fatec.exceptions;

public class GoogleLoginException extends RuntimeException {
    public GoogleLoginException(){
        super("error while authenticating user with google api");
    }
}