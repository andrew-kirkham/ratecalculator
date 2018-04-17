package com.config;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

public class ApplicationConfigTest {

    @Test
    public void readRateConfig() {
        ApplicationConfig config = new ApplicationConfig(ClassLoader.getSystemClassLoader().getResource("rates.json"));
        Assert.assertNotEquals(new HashMap<>(), config.getRateRanges());
    }

    @Test
    public void readNonExistantRateConfig() {
        ApplicationConfig config = new ApplicationConfig(ClassLoader.getSystemClassLoader().getResource("asdfasdfa"));
        //badly configured shouldnt crash app. instead just have blank configs
        Assert.assertEquals(new HashMap<>(), config.getRateRanges());
    }
}