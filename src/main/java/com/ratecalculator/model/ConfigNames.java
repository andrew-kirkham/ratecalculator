package com.ratecalculator.model;

/**
 * ConfigNames
 *
 * Config file names as they are referenced in the resources folder
 */
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
