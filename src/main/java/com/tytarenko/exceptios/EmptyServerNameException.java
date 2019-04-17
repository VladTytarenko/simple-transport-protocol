package com.tytarenko.exceptios;

public class EmptyServerNameException extends Exception {

    private String message;

    public EmptyServerNameException(String message) {
        this.message = message;
    }
}
