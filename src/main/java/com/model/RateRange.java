package com.model;

import lombok.Data;

@Data
public class RateRange {

    private TimeRange rangeRateIsValidFor;
    private int price;
}
