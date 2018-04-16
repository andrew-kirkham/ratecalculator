package com.model;

import lombok.Data;

import java.time.LocalTime;

@Data
public class TimeRange {

    private LocalTime startTime;
    private LocalTime endTime;

    public TimeRange(final LocalTime startTime, final LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public boolean containsRange(final TimeRange otherRange) {
        return (startTime.isBefore(otherRange.getStartTime()) && endTime.isAfter(otherRange.getEndTime()));
    }

}
