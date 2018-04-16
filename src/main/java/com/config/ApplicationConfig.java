package com.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.DayOfWeek;
import com.model.RateRange;
import com.model.TimeRange;
import com.model.config.Rate;
import com.model.config.RateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApplicationConfig {

    private static final ObjectMapper mapper = new ObjectMapper();
    private static final Logger LOG = LoggerFactory.getLogger(ApplicationConfig.class);
    private static ApplicationConfig instance;
    private transient RateConfig rateConfig = new RateConfig();
    //day, valid rates for that day
    private transient Map<DayOfWeek, List<RateRange>> rateRanges = new HashMap<>();

    public ApplicationConfig() {
        final String configLocation = "./";
        LOG.info("config location={}", configLocation);
        try {
            rateConfig = readFileIfExists(configLocation + "rates.json", RateConfig.class);
            convertRateConfig(rateConfig);
        } catch (Exception e) {
            LOG.warn("Failed to read config with error. Using default values", e);
        }
    }

    private void convertRateConfig(RateConfig rateConfig) {
        for (final Rate rate : rateConfig.getRates()) {
            final String[] days = rate.getDays().split(",");
            for (final String day : days){
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
    }

    public static ApplicationConfig getInstance() {
        if (instance == null) {
            instance = new ApplicationConfig();
        }
        return instance;
    }

    private <T> T readFileIfExists(final String path, final Class<T> clazz) throws IOException, IllegalAccessException, InstantiationException {
        final File f = new File(path);
        if (f.exists()) {
            return mapper.readValue(f, clazz);
        }
        LOG.warn("Unable to find configFile for {} at {}, initializing to default values", clazz.getCanonicalName(), f.getAbsolutePath());
        return clazz.newInstance();
    }

    public Map<DayOfWeek, List<RateRange>> getRateRanges() {
        return rateRanges;
    }
}


