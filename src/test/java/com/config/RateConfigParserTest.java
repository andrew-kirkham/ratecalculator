package com.config;

import com.model.DayOfWeek;
import com.model.RateRange;
import com.model.config.Rate;
import com.model.config.RateConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public class RateConfigParserTest {

    private RateConfig rateConfig;
    private RateConfigParser rateConfigParser = new RateConfigParser();
    private LocalTime ONE_AM = LocalTime.of(1, 0, 0);
    private LocalTime NOON = LocalTime.of(12, 0, 0);
    private int RATE = 100;

    @Before
    public void setup() {
        rateConfig = new RateConfig();
    }

    @Test
    public void convertRateConfig() {
        Rate r = new Rate();
        r.setDays("mon");
        r.setPrice(RATE);
        r.setTimes("0100-1200");
        rateConfig.setRates(new Rate[]{r});
        final Map<DayOfWeek, List<RateRange>> actual = rateConfigParser.convertRateConfig(rateConfig);

        Assert.assertTrue(actual.containsKey(DayOfWeek.MONDAY));
        List<RateRange> ranges = actual.get(DayOfWeek.MONDAY);
        Assert.assertEquals(1, ranges.size());
        Assert.assertEquals(ONE_AM, ranges.get(0).getRangeRateIsValidFor().getStartTime());
        Assert.assertEquals(NOON, ranges.get(0).getRangeRateIsValidFor().getEndTime());
        Assert.assertEquals(RATE, ranges.get(0).getPrice());
    }

    @Test
    public void convertRateConfigMultiDay() {
        Rate r = new Rate();
        r.setDays("mon,tues");
        r.setPrice(RATE);
        r.setTimes("0100-1200");
        rateConfig.setRates(new Rate[]{r});
        final Map<DayOfWeek, List<RateRange>> actual = rateConfigParser.convertRateConfig(rateConfig);

        Assert.assertTrue(actual.containsKey(DayOfWeek.MONDAY));
        Assert.assertTrue(actual.containsKey(DayOfWeek.TUESDAY));
    }
}