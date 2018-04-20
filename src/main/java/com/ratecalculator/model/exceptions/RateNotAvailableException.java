package com.ratecalculator.model.exceptions;


import java.net.HttpURLConnection;

public class RateNotAvailableException extends ApplicationException {
    public RateNotAvailableException(final String message) {
        super(HttpURLConnection.HTTP_NOT_FOUND, message);
    }
}
