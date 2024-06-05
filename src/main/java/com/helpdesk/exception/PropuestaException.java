package com.helpdesk.exception;

public class PropuestaException extends RuntimeException {
    private String errorMessage;

    public PropuestaException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
