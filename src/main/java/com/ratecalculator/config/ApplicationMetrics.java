package com.ratecalculator.config;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.health.HealthCheckRegistry;

/**
 * ApplicationMetrics
 * Static Registries for metrics
 */
public class ApplicationMetrics {

    private static final MetricRegistry metrics = new MetricRegistry();
    private static final HealthCheckRegistry healthChecks = new HealthCheckRegistry();

    public static MetricRegistry getMetrics() {
        return metrics;
    }

    public static HealthCheckRegistry getHealthChecks() {
        return healthChecks;
    }

}
