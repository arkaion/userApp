package com.example.demo.utility.Exceptions;

public class ResourceAlreadyCreatedException extends RuntimeException {
    public ResourceAlreadyCreatedException(String message) {
        super(message);
    }
}