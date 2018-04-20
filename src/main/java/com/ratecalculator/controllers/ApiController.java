package com.ratecalculator.controllers;

import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import com.ratecalculator.config.ApplicationMetrics;
import com.ratecalculator.handlers.ApiHandler;
import com.ratecalculator.model.exceptions.ApplicationException;
import com.ratecalculator.model.rest.RangeRequest;
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

import static com.codahale.metrics.MetricRegistry.name;

/**
 * ApiController
 *
 * Main controller for the api
 */
@Path("/")
@Api(value = "api")
public class ApiController {

    private final static Logger LOG = LoggerFactory.getLogger(ApiController.class);
    private final transient ApiHandler apiHandler = new ApiHandler();
    private final transient Meter requests;
    private final transient Timer responses;

    public ApiController() {
        final MetricRegistry metrics =  ApplicationMetrics.getMetrics();
        requests = metrics.meter("requests");
        responses = metrics.timer(name(ApiController.class, "responses"));
    }

    @POST
    @Path("/rate")
    @ApiOperation(value = "get rate for time range")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "OK", response = Integer.class),
            @ApiResponse(code = HttpURLConnection.HTTP_INTERNAL_ERROR, message = "Internal server error")})
    @Consumes(value = {MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces(value = {MediaType.TEXT_PLAIN})
    public int getRateForTimeRange(final RangeRequest time) throws ApplicationException {
        requests.mark();
        Timer.Context context = responses.time();
        LOG.info("getting rate for time={}", time);
        try {
            return apiHandler.calculateRate(time);
        } finally {
            context.stop();
        }
    }
}
