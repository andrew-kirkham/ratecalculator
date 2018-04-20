package com.ratecalculator.model.config;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RateConfig {
    private Rate[] rates = new Rate[0];
}
