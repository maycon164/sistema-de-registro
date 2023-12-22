package com.fatec.exceptions;

public class UserNotFound extends NotFound{
    public UserNotFound(){
        super("User not found in internal database");
    }
}
