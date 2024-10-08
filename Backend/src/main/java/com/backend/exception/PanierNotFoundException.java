package com.backend.exception;

public class PanierNotFoundException extends RuntimeException {
    public PanierNotFoundException(String message) {
        super(message);
    }
}