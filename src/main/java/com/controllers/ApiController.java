package com.controllers;

import com.handlers.ApiHandler;
import com.model.exceptions.ApplicationErrorMessage;
import com.model.exceptions.ApplicationException;
import com.model.rest.RangeRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.net.HttpURLConnection;

@Path("/api")
@Api
public class ApiController {

    private final static Logger LOG = LoggerFactory.getLogger(ApiController.class);
    private final transient ApiHandler apiHandler = new ApiHandler();

    @POST
    @Path("/rate")
    @ApiOperation(value = "get rate for time range")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "OK", response = Integer.class),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Invalid range", response = ApplicationErrorMessage.class),
            @ApiResponse(code = HttpURLConnection.HTTP_NOT_FOUND, message = "Rate not found for range", response = ApplicationErrorMessage.class),
            @ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal server error")})
    @Consumes(value = {MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.TEXT_PLAIN})
    public int getRateForTimeRange(final RangeRequest time) throws ApplicationException {
        LOG.info("getting rate for time={}", time);
        return apiHandler.handleRate(time);
    }
}
