package com.handlers;

import com.codahale.metrics.health.HealthCheck;
import com.config.ApplicationConfig;

public class HealthCheckHandler extends HealthCheck {

    private ApplicationConfig config = ApplicationConfig.getInstance();

    @Override
    protected Result check() throws Exception {
        if (config.getRateRanges().isEmpty()){
            return HealthCheck.Result.unhealthy("Unable to load config files or config files empty");
        } else {
            return HealthCheck.Result.healthy();
        }
    }
}
