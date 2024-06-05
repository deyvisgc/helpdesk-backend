package com.helpdesk.exception;

public class UsuarioException extends RuntimeException {
    private String errorMessage;

    public UsuarioException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
