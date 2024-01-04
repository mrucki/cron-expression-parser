package com.mrucki.cron;

import com.mrucki.cron.parser.CronField;
import com.mrucki.cron.parser.ExpressionParser;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ExpressionParserTest {

    private ExpressionParser cut = new ExpressionParser();

    @Test
    void parseExpression() {
        Map<CronField, String> cronFields = cut.parseExpression("*/15 0 1,15 * 1-5 /usr/bin/find");

        assertEquals("*/15", cronFields.get(CronField.MINUTE));
        assertEquals("0", cronFields.get(CronField.HOUR));
        assertEquals("1,15", cronFields.get(CronField.DAY_OF_MONTH));
        assertEquals("*", cronFields.get(CronField.MONTH));
        assertEquals("1-5", cronFields.get(CronField.DAY_OF_WEEK));
        assertEquals("/usr/bin/find", cronFields.get(CronField.CMD));
    }

    @Test
    void parseExpressionWhenNull() {
        assertThrows(NullPointerException.class, () -> cut.parseExpression(null));
    }

    @Test
    void parseExpressionWhenIncorrectLength() {
        assertThrows(IllegalArgumentException.class, () -> cut.parseExpression("*/15 0 1,15 * /usr/bin/find"));
    }
}