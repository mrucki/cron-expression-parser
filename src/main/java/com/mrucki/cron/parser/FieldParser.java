package com.mrucki.cron.parser;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FieldParser {

    public String parseField(CronField cronField, String cronValue) {
        if(cronField == CronField.CMD) {
            return cronValue;
        } else if(cronValue.contains("*/")) {
            return parseEveryValue(cronField, cronValue);
        } else if (cronValue.equals("*")) {
            return parseAllValues(cronField);
        } else if (cronValue.contains(",")) {
            return parseListedValues(cronField, cronValue);
        } else if (cronValue.contains("-")) {
            return parseRangeValue(cronField, cronValue);
        }

        return cronValue;
    }

    private String parseEveryValue(CronField cronField, String cronEveryValue) {
        int everyValue = Integer.parseInt(cronEveryValue.replace("*/", ""));

        StringBuilder value = new StringBuilder();
        for(int step = cronField.getMin(); step<=cronField.getMax(); step=step+everyValue) {
            value.append(" ").append(step);
        }

        return value.toString().trim();
    }

    private String parseAllValues(CronField cronField) {
        return IntStream.range(cronField.getMin(), cronField.getMax()+1)
                .mapToObj(Integer::toString)
                .collect(Collectors.joining(" "));
    }

    private String parseListedValues(CronField cronField, String cronValue) {
        String[] values = cronValue.split(",");
        return Arrays.stream(values)
                .map(Integer::parseInt)
                .filter(v -> v>=cronField.getMin() && v<=cronField.getMax())
                .map(String::valueOf)
                .collect(Collectors.joining(" "));
    }

    private String parseRangeValue(CronField cronField, String cronValue) {
        String[] range = cronValue.split("-");
        int rangeFrom = Math.max(Integer.parseInt(range[0]), cronField.getMin());
        int rangeTo = Math.min(Integer.parseInt(range[1]), cronField.getMax());

        return IntStream.range(rangeFrom, rangeTo+1)
                .mapToObj(Integer::toString)
                .collect(Collectors.joining(" "));
    }
}
