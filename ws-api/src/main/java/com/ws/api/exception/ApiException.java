package com.ws.api.exception;

public class ApiException extends Exception {

    /* --- Members --- */

    private String message;

    /* --- Constructors --- */

    public ApiException(String message) {
        this.message = message;
    }

    /* --- Getters / Setters --- */

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
