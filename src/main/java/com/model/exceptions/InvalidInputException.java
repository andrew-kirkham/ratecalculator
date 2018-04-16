package com.model.exceptions;


public class InvalidInputException extends ApplicationException {
    public InvalidInputException(final String message) {
        super(400, message);
    }
}
