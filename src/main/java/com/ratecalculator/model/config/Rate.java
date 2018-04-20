package com.ratecalculator.model.config;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties
@Data
public class Rate {
    @JsonProperty("days")
    private String days;

    @JsonProperty("times")
    private String times;

    @JsonProperty("price")
    private int price;
}
