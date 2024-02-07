package com.superhero.Superhero.exception;

public class EntityNotFoundException extends RuntimeException{
    
    public EntityNotFoundException(String message) {
        super(message);
    }
}
