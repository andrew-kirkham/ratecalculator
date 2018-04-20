package com.ratecalculator.handlers;

import com.ratecalculator.model.exceptions.ApplicationException;
import com.ratecalculator.model.exceptions.InvalidInputException;
import com.ratecalculator.model.exceptions.RateNotAvailableException;
import com.ratecalculator.model.rest.RangeRequest;
import org.junit.Assert;
import org.junit.Test;

public class ApiHandlerTest {

    private ApiHandler handler = new ApiHandler();

    @Test
    public void handleRate() throws ApplicationException {
        String from = "2015-07-01T12:00:00Z";
        String to = "2015-07-01T13:00:00Z";
        RangeRequest r = new RangeRequest();
        r.setFromTime(from);
        r.setToTime(to);

        int rate = handler.handleRate(r);
        Assert.assertEquals(1500, rate);
    }

    @Test(expected = InvalidInputException.class)
    public void handleInvalidRate() throws ApplicationException {
        String from = "asdf";
        String to = "2015-07-01T12:00:00Z";
        RangeRequest r = new RangeRequest();
        r.setFromTime(from);
        r.setToTime(to);

        handler.handleRate(r);
        //asserting exception thrown
    }

    @Test(expected = InvalidInputException.class)
    public void fromTimeBeforeToTime() throws ApplicationException {
        String from = "2015-07-01T12:00:00Z";
        String to = "2015-07-01T11:00:00Z";
        RangeRequest r = new RangeRequest();
        r.setFromTime(from);
        r.setToTime(to);

        handler.handleRate(r);
        //asserting exception thrown
    }


    @Test(expected = RateNotAvailableException.class)
    public void noRateForTime() throws ApplicationException {
        String from = "2015-07-01T00:00:00Z";
        String to = "2015-07-01T01:00:00Z";
        RangeRequest r = new RangeRequest();
        r.setFromTime(from);
        r.setToTime(to);

        handler.handleRate(r);
        //asserting exception thrown
    }
}