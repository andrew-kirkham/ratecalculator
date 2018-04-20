package com.ratecalculator.model;

import org.junit.Assert;
import org.junit.Test;

public class DayOfWeekTest {

    @Test
    public void convertDayOfWeekFromString() {
        DayOfWeek dow = DayOfWeek.fromName("wed");
        Assert.assertEquals(DayOfWeek.WEDNESDAY, dow);
    }

    @Test
    public void convertDayOfWeekFromStringCaps() {
        DayOfWeek dow = DayOfWeek.fromName("WED");
        Assert.assertEquals(DayOfWeek.WEDNESDAY, dow);
    }

    @Test(expected = IllegalArgumentException.class)
    public void noDayOfWeekForString() {
        DayOfWeek dow = DayOfWeek.fromName("asdfadsf");
        //assert exception
    }
}