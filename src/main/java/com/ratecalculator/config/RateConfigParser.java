package com.ratecalculator.config;

import com.ratecalculator.model.DayOfWeek;
import com.ratecalculator.model.RateRange;
import com.ratecalculator.model.TimeRange;
import com.ratecalculator.model.config.Rate;
import com.ratecalculator.model.config.RateConfig;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * RateConfigParser
 *
 * Parse the rates.json config file and convert it to more easily usable structure
 */
public class RateConfigParser {

    /**
     * Convert a rateConfig structure to a Map. This does NOT validate the rateconfig
     *
     * @param rateConfig - the json config
     * @return Map<DayOfWeek, List of rates for that day>
     */
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
        //TODO add validation to the structure and to times
        final RateRange range = new RateRange();
        range.setPrice(rate.getPrice());

        final String[] times = rate.getTimes().split("-");

        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
        final LocalTime start = LocalTime.parse(times[0], formatter);
        final LocalTime end = LocalTime.parse(times[1], formatter);

        range.setRangeRateIsValidFor(new TimeRange(start, end));

        final List<RateRange> ranges = rateRanges.getOrDefault(DayOfWeek.fromName(day), new ArrayList<>());
        ranges.add(range);
        rateRanges.put(DayOfWeek.fromName(day), ranges);
    }
}
