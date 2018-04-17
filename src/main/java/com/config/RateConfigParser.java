package com.config;

import com.model.DayOfWeek;
import com.model.RateRange;
import com.model.TimeRange;
import com.model.config.Rate;
import com.model.config.RateConfig;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RateConfigParser {

    public Map<DayOfWeek, List<RateRange>> convertRateConfig(final RateConfig rateConfig) {
        final Map<DayOfWeek, List<RateRange>> rateRanges = new HashMap<>();
        for (final Rate rate : rateConfig.getRates()) {
            parseRate(rateRanges, rate);
        }
        return rateRanges;
    }

    private void parseRate(final Map<DayOfWeek, List<RateRange>> rateRanges, final Rate rate) {
        final String[] days = rate.getDays().split(",");
        for (final String day : days) {
            parseDay(rateRanges, rate, day);
        }
    }

    private void parseDay(final Map<DayOfWeek, List<RateRange>> rateRanges, final Rate rate, final String day) {
        final RateRange r = new RateRange();
        r.setPrice(rate.getPrice());

        final String[] times = rate.getTimes().split("-");

        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
        final LocalTime start = LocalTime.parse(times[0], formatter);
        final LocalTime end = LocalTime.parse(times[1], formatter);

        r.setRangeRateIsValidFor(new TimeRange(start, end));

        final List<RateRange> ranges = rateRanges.getOrDefault(DayOfWeek.fromName(day), new ArrayList<>());
        ranges.add(r);
        rateRanges.put(DayOfWeek.fromName(day), ranges);
    }
}
