package com.ratecalculator;

import com.ratecalculator.controllers.ApiController;
import com.ratecalculator.controllers.AppExceptionMapper;
import com.ratecalculator.controllers.MetricsController;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;

import javax.ws.rs.core.Application;

public class IntegrationTestHelper extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(ApiController.class, AppExceptionMapper.class, MetricsController.class);
    }
}
