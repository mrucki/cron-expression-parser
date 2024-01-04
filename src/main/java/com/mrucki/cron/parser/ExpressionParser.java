package com.mrucki.cron.parser;

import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class ExpressionParser {

    private static final int CRON_FIELDS_LENGTH = 6;

    public Map<CronField, String> parseExpression(String expression) {
        String[] cronFields = expression.split(" ");

        Objects.requireNonNull(cronFields);
        if(cronFields.length != CRON_FIELDS_LENGTH) {
            throw new IllegalArgumentException("Cron expression should have "+CRON_FIELDS_LENGTH+" fields.");
        }

        TreeMap<CronField, String> cronMap = new TreeMap<>();
        cronMap.put(CronField.MINUTE, cronFields[0]);
        cronMap.put(CronField.HOUR, cronFields[1]);
        cronMap.put(CronField.DAY_OF_MONTH, cronFields[2]);
        cronMap.put(CronField.MONTH, cronFields[3]);
        cronMap.put(CronField.DAY_OF_WEEK, cronFields[4]);
        cronMap.put(CronField.CMD, cronFields[5]);

        return cronMap;
    }
}
