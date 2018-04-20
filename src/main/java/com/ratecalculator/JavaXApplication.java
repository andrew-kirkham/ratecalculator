package com.ratecalculator;

import com.ratecalculator.config.ApplicationMetrics;
import com.ratecalculator.controllers.ApiController;
import com.ratecalculator.controllers.AppExceptionMapper;
import com.ratecalculator.controllers.MetricsController;
import com.ratecalculator.handlers.HealthCheckHandler;
import io.swagger.jaxrs.config.BeanConfig;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/")
public class JavaXApplication extends Application {

    public JavaXApplication() {
        registerMetrics();
        setupSwagger();
    }

    private static void registerMetrics() {
        ApplicationMetrics.getHealthChecks().register("config", new HealthCheckHandler());
    }

    private void setupSwagger() {
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.2");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost("localhost:8080");
        beanConfig.setBasePath("/api");
        beanConfig.setResourcePackage("com.ratecalculator");
        beanConfig.setScan(true);
    }

    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<>();
        // register root resource
        classes.add(ApiController.class);
        classes.add(AppExceptionMapper.class);
        classes.add(MetricsController.class);

        classes.add(io.swagger.jaxrs.listing.SwaggerSerializers.class);
        classes.add(io.swagger.jaxrs.listing.ApiListingResource.class);

        return classes;
    }
}