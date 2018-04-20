package com.ratecalculator.model.exceptions;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class ApplicationErrorMessage {
    private final transient String message;
    private final transient int code;

    public ApplicationErrorMessage(final ApplicationException ex) {
        this.message = ex.getMessage();
        this.code = ex.getStatus();
    }
}
