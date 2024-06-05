package com.helpdesk.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(PropuestaException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handlePropuestaException(PropuestaException ex) {
        System.err.println("Error: " + ex.getMessage());
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getErrorMessage());
    }

    @ExceptionHandler(RequerimientoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleRequerimientoException(RequerimientoException ex) {
        System.err.println("Error: " + ex.getMessage());
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getErrorMessage());
    }
    @ExceptionHandler(UsuarioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleUsuarioException(UsuarioException ex) {
        System.err.println("Error: " + ex.getMessage());
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getErrorMessage());
    }

    @ExceptionHandler(RecursoNoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleRecursoNoEncontradoException(RecursoNoEncontradoException ex) {
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse handleInternalServerError(Exception ex) {
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }
    public static class ErrorResponse {
        private int status;
        private String message;

        public ErrorResponse(int status, String message) {
            this.status = status;
            this.message = message;
        }

        public int getStatus() {
            return status;
        }

        public String getMessage() {
            return message;
        }
    }


}
