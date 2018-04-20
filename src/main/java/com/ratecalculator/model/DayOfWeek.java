package com.ratecalculator.model;

public enum DayOfWeek {
    MONDAY("mon"),
    TUESDAY("tues"),
    WEDNESDAY("wed"),
    THURSDAY("thurs"),
    FRIDAY("fri"),
    SATURDAY("sat"),
    SUNDAY("sun");

    private String abbreviation;

    DayOfWeek(final String abbr) {
        this.abbreviation = abbr;
    }

    public static DayOfWeek fromName(final String name) {
        for (final DayOfWeek dow : DayOfWeek.values()) {
            if (dow.getAbbreviation().equalsIgnoreCase(name)) {
                return dow;
            }
        }
        throw new IllegalArgumentException(String.format("No DayOfWeek found with name %s", name));
    }

    String getAbbreviation() {
        return this.abbreviation;
    }
}
