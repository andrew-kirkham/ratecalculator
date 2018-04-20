package com.ratecalculator.controllers;

import com.ratecalculator.IntegrationTestHelper;
import org.junit.Assert;
import org.junit.Test;

public class MetricsControllerIntegrationTest extends IntegrationTestHelper {

    @Test
    public void getTimers() {
        String response = target("/metrics/responseTime").request().get(String.class);
        Assert.assertFalse(response.isEmpty());
    }

    @Test
    public void getMeters() {
        String response = target("/metrics/meter").request().get(String.class);
        Assert.assertFalse(response.isEmpty());
    }

    @Test
    public void getHealthCheck() {
        String response = target("/metrics/healthCheck").request().get(String.class);
        Assert.assertFalse(response.isEmpty());
    }
}
