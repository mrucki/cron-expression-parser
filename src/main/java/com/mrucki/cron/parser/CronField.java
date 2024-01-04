package com.mrucki.cron.parser;

import java.util.Arrays;

public enum CronField {
    MINUTE("minute",0, 59),
    HOUR("hour", 0, 23),
    DAY_OF_MONTH("day of month",1, 31),
    MONTH("month",1, 12),
    DAY_OF_WEEK("day of week", 1, 7),
    CMD("command", 0, 0);

    private final String label;
    private final int min;
    private final int max;

    CronField(String label, int min, int max) {
        this.label = label;
        this.min = min;
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public String getLabel() {
        return label;
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public static int calculateLongestLabel() {
        return Arrays.stream(CronField.values())
                .mapToInt(value -> value.name().length())
                .max()
                .getAsInt();
    }
}
