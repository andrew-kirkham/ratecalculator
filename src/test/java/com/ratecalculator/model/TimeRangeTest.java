package com.ratecalculator.model;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalTime;

public class TimeRangeTest {

    private LocalTime t0 = LocalTime.of(1, 1, 1);
    private LocalTime t1 = LocalTime.of(2, 2, 2);
    private LocalTime t2 = LocalTime.of(3, 3, 3);
    private LocalTime t3 = LocalTime.of(4, 4, 4);

    @Test
    public void containsRange() {
        TimeRange r = new TimeRange(t0, t3);
        TimeRange r2 = new TimeRange(t1, t2);
        Assert.assertTrue(r.containsRange(r2));
    }

    @Test
    public void doesNotContainRange() {
        TimeRange r = new TimeRange(t0, t3);
        TimeRange r2 = new TimeRange(t1, t2);
        Assert.assertFalse(r2.containsRange(r));
    }

    @Test
    public void overlappingStartRangeNotContained() {
        TimeRange r = new TimeRange(t0, t2);
        TimeRange r2 = new TimeRange(t1, t3);
        Assert.assertFalse(r.containsRange(r2));
    }

    @Test
    public void overlappingEndRangeNotContained() {
        TimeRange r = new TimeRange(t0, t2);
        TimeRange r2 = new TimeRange(t1, t3);
        Assert.assertFalse(r2.containsRange(r));
    }
}