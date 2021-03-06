package com.ratecalculator.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ratecalculator.model.ConfigNames;
import com.ratecalculator.model.DayOfWeek;
import com.ratecalculator.model.RateRange;
import com.ratecalculator.model.config.RateConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Application Config
 * Contains all configuration for this application
 * Config files are json files added to src/main/resources
 */
public class ApplicationConfig {

    private static final ObjectMapper mapper = new ObjectMapper();
    private static final Logger LOG = LoggerFactory.getLogger(ApplicationConfig.class);
    private static ApplicationConfig instance;
    //day, valid rates for that day
    private transient Map<DayOfWeek, List<RateRange>> rateRanges = new HashMap<>();

    public ApplicationConfig(final URL configLocation) {
        LOG.info("config root path: {}", getClass().getResource("/").getPath());
        try {
            readRateConfig(configLocation.getPath());
        } catch (Exception e) {
            LOG.warn("Failed to read config using default values. error:", e);
        }
    }

    public static ApplicationConfig getInstance() {
        if (instance == null) {
            instance = new ApplicationConfig(ApplicationConfig.class.getResource(ConfigNames.RATE_CONFIG.getFileName()));
        }
        return instance;
    }

    private void readRateConfig(final String path) throws IllegalAccessException, IOException, InstantiationException {
        final RateConfig rateConfig = readFileIfExists(path, RateConfig.class);
        final RateConfigParser parser = new RateConfigParser();
        rateRanges = parser.convertRateConfig(rateConfig);
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


