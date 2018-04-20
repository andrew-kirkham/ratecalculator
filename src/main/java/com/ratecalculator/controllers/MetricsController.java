package com.ratecalculator.controllers;

import com.codahale.metrics.Meter;
import com.codahale.metrics.Timer;
import com.codahale.metrics.health.HealthCheck;
import com.ratecalculator.config.ApplicationMetrics;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.SortedMap;

/**
 * MetricsController
 *
 * REST endpoint for Metrics
 */
@Path("/metrics")
@Api(value = "metrics")
public class MetricsController {

    private final static Logger LOG = LoggerFactory.getLogger(MetricsController.class);

    @GET
    @Path("/responseTime")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "get timing metrics")
    public SortedMap<String, Timer> getTimers() {
        return ApplicationMetrics.getMetrics().getTimers();
    }

    @GET
    @Path("/meter")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "get timing metrics")
    public SortedMap<String, Meter> getMeters() {
        return ApplicationMetrics.getMetrics().getMeters();
    }

    @GET
    @Path("/healthCheck")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "get health check")
    public SortedMap<String, HealthCheck.Result> getHealth() {
        LOG.info("hc={}", ApplicationMetrics.getHealthChecks().getNames());
        return ApplicationMetrics.getHealthChecks().runHealthChecks();
    }

}
