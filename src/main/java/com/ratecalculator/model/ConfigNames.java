package com.ratecalculator.model;

public enum ConfigNames {
    RATE_CONFIG("/rates.json");

    private String fileName;

    ConfigNames(final String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return this.fileName;
    }
}
