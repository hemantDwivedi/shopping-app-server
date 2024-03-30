package com.dev.shoppingapp.exception;

public class ApiException extends RuntimeException{
    public ApiException(String description) {
        super(description);
    }
}
