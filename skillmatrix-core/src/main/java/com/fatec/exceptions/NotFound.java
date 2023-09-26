package com.fatec.exceptions;

public class NotFound extends RuntimeException {
    public NotFound(String message){
        super(message);
    }
}