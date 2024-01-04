package com.mrucki.cron;

import com.mrucki.cron.parser.CronField;
import com.mrucki.cron.parser.ExpressionParser;
import com.mrucki.cron.parser.FieldParser;

import java.util.Map;
import java.util.Objects;

public class CronExpressionParser {
    ExpressionParser expressionParser = new ExpressionParser();
    FieldParser fieldParser = new FieldParser();

    public static void main(String[] args) {
        Objects.requireNonNull(args);
        String cronExpression = args[0];

        CronExpressionParser cronExpressionParser = new CronExpressionParser();
        cronExpressionParser.parseAndDisplay(cronExpression);
    }

    private void parseAndDisplay(String cronExpression) {
        Map<CronField, String> cronFields =  expressionParser.parseExpression(cronExpression);
        int longestLabel = CronField.calculateLongestLabel();

        cronFields.entrySet().stream().forEach(entry -> {
            String fieldValue = fieldParser.parseField(entry.getKey(), entry.getValue());
            String label = entry.getKey().getLabel();
            int numberOfSpaces = longestLabel-label.length();
            String formattedLabel = String.format("%-" + (label.length() + numberOfSpaces) + "s", label);

            System.out.println(formattedLabel+"   "+fieldValue);
        });

    }
}