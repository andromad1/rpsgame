package com.example.nobeltesttask.rpsgame.exceptions;

public class InvalidGameStatusException extends RuntimeException {
    public InvalidGameStatusException() {
    }

    public InvalidGameStatusException(String message) {
        super(message);
    }
}
