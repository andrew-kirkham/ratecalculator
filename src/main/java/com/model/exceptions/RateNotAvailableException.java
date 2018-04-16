package com.model.exceptions;

import org.eclipse.jetty.http.HttpStatus;

public class RateNotAvailableException extends ApplicationException {
    public RateNotAvailableException(final String message) {
        super(HttpStatus.NOT_FOUND_404, message);
    }
}
