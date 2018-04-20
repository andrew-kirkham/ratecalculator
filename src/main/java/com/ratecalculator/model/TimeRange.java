package com.ratecalculator.model;

import lombok.Data;

import java.time.LocalTime;

@Data
public class TimeRange {

    private transient LocalTime startTime;
    private transient LocalTime endTime;

    public TimeRange(final LocalTime startTime, final LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public boolean containsRange(final TimeRange otherRange) {
        return (startTime.isBefore(otherRange.getStartTime()) && endTime.isAfter(otherRange.getEndTime()));
    }

}
