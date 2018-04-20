package com.ratecalculator.controllers;

import com.ratecalculator.model.exceptions.ApplicationErrorMessage;
import com.ratecalculator.model.exceptions.ApplicationException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.net.HttpURLConnection;

/**
 * AppExceptionMapper
 * <p>
 * Maps Exceptions thrown by controllers to a JSON response
 */
@Provider
public class AppExceptionMapper implements ExceptionMapper<Exception> {

    public Response toResponse(final Exception ex) {
        ApplicationException exceptionToReturn;
        if (ex instanceof ApplicationException) {
            exceptionToReturn = (ApplicationException) ex;
        } else {
            exceptionToReturn = new ApplicationException(HttpURLConnection.HTTP_INTERNAL_ERROR, "internal server error");
        }

        return Response.status(exceptionToReturn.getStatus())
                .entity(new ApplicationErrorMessage(exceptionToReturn))
                .type(MediaType.APPLICATION_JSON).
                        build();
    }

}
