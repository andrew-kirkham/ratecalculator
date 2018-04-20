package com.ratecalculator.model.exceptions;


import java.net.HttpURLConnection;

public class InvalidInputException extends ApplicationException {
    public InvalidInputException(final String message) {
        super(HttpURLConnection.HTTP_BAD_REQUEST, message);
    }
}
