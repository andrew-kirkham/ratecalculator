package com.controllers;

import com.model.exceptions.ApplicationErrorMessage;
import com.model.exceptions.ApplicationException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class AppExceptionMapper implements ExceptionMapper<ApplicationException> {

    public Response toResponse(final ApplicationException ex) {
        return Response.status(ex.getStatus())
                .entity(new ApplicationErrorMessage(ex))
                .type(MediaType.APPLICATION_JSON).
                        build();
    }

}
