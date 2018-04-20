package com.ratecalculator.handlers;

import com.codahale.metrics.health.HealthCheck;
import com.ratecalculator.config.ApplicationConfig;

/**
 * HealthCheckHandler
 *
 * Handle any healthcheck for the application
 */
public class HealthCheckHandler extends HealthCheck {

    private final transient ApplicationConfig config = ApplicationConfig.getInstance();

    @Override
    protected Result check() {
        //check we have a config file
        if (config.getRateRanges().isEmpty()){
            return HealthCheck.Result.unhealthy("Unable to load config files or config files empty");
        } else {
            return HealthCheck.Result.healthy();
        }
    }
}
