package com.helpdesk.exception;

public class RequerimientoException extends RuntimeException {
    private String errorMessage;

    public RequerimientoException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
