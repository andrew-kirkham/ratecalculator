package com.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/api")
@Api
@Produces(value="text/plain")
public class ApiController {
    private final static Logger LOG = LoggerFactory.getLogger(ApiController.class);

    @GET
    @Path("/{time}")
    @ApiOperation(value="get rate for time range")
    public int getRateForTimeRange(@PathParam("time") final String timeA) {
        LOG.info("getting rate for time range={}", timeA);
        return 0;
    }
}
