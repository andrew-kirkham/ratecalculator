package com.ratecalculator.model.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ApplicationException extends Exception {
    private final transient int status;
    private final transient String message;

    public ApplicationException(final int httpStatusCode, final String message){
        this.status = httpStatusCode;
        this.message = message;
    }
}
