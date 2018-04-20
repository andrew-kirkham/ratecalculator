package com.ratecalculator.model;

/**
 * DayOfWeek
 *
 * Day of week as inputted in the config file
 */
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

    /**
     * Convert the given string to a day of week
     * @param name - the string to convert
     * @return a DayOfWeek representing the string
     * @throws IllegalArgumentException - if the string is not a valid day of week
     */
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
