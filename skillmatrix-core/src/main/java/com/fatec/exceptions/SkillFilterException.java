package com.fatec.exceptions;

public class SkillFilterException extends RuntimeException{
    public SkillFilterException() {
        super("Error while parsing skill filter");
    }
}
