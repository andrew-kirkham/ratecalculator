package com.ratecalculator.controllers;

import com.ratecalculator.IntegrationTestHelper;
import com.ratecalculator.model.rest.RangeRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class ApiControllerIntegrationTest extends IntegrationTestHelper {

    private static final int EXPECTED_RATE = 1500;
    private RangeRequest validRange = new RangeRequest();
    private RangeRequest invalidRange = new RangeRequest();
    
    @Before
    public void setup() {
        String from = "2015-07-01T12:00:00Z";
        String to = "2015-07-01T13:00:00Z";
        validRange.setFromTime(from);
        validRange.setToTime(to);
        invalidRange.setFromTime(from);
        invalidRange.setToTime("2015-07-01T23:00:00Z");
    }

    @Test
    public void postNothing() {
        Response response = target("/rate").request().post(null);
        Assert.assertEquals(400, response.getStatus());
    }

    @Test
    public void postEmptyJson() {
        Response response = target("/rate").request().post(Entity.entity(new RangeRequest(), MediaType.APPLICATION_JSON_TYPE));
        Assert.assertEquals(400, response.getStatus());
    }

    @Test
    public void postEmptyXml() {
        Response response = target("/rate").request().post(Entity.entity(new RangeRequest(), MediaType.APPLICATION_XML_TYPE));
        Assert.assertEquals(400, response.getStatus());
    }

    @Test
    public void postValidJson() {
        Integer response = target("/rate").request().post(Entity.entity(validRange, MediaType.APPLICATION_JSON_TYPE), Integer.class);
        Assert.assertEquals(EXPECTED_RATE, response.intValue());
    }

    @Test
    public void postValidXml() {
        Integer response = target("/rate").request().post(Entity.entity(validRange, MediaType.APPLICATION_XML_TYPE), Integer.class);
        Assert.assertEquals(EXPECTED_RATE, response.intValue());
    }

    @Test
    public void postValidJsonBadRange() {
        Response response = target("/rate").request().post(Entity.entity(invalidRange, MediaType.APPLICATION_JSON_TYPE));
        Assert.assertEquals(404, response.getStatus());
    }
    
    @Test
    public void postValidXmlBadRange() {
        Response response = target("/rate").request().post(Entity.entity(invalidRange, MediaType.APPLICATION_XML_TYPE));
        Assert.assertEquals(404, response.getStatus());
    }
}
