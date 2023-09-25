package com.fatec.exceptions;

public class SnapshotException extends RuntimeException{

    public SnapshotException() {
        super("Error while saving user snapshot");
    }
}
