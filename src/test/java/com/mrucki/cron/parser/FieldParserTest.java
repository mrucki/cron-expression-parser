package com.mrucki.cron.parser;

import com.mrucki.cron.parser.CronField;
import com.mrucki.cron.parser.FieldParser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class FieldParserTest {

    private final FieldParser cut = new FieldParser();

    @Test
    void parseField() {
        String minute = "*/15";
        String hour = "10";
        String dayOfMonth = "0,1,15,31,40";
        String month = "*";
        String dayOfWeek = "1-6";
        String cmd = "/usr/bin/find";

        assertEquals("0 15 30 45", cut.parseField(CronField.MINUTE, minute));
        assertEquals("10", cut.parseField(CronField.HOUR, hour));
        assertEquals("1 15 31", cut.parseField(CronField.DAY_OF_MONTH, dayOfMonth));
        assertEquals("1 2 3 4 5 6 7 8 9 10 11 12", cut.parseField(CronField.MONTH, month));
        assertEquals("1 2 3 4 5 6", cut.parseField(CronField.DAY_OF_WEEK, dayOfWeek));
        assertEquals("/usr/bin/find", cut.parseField(CronField.CMD, cmd));
    }

    @ParameterizedTest
    @CsvSource({
            "0,60,MINUTE,*/17,0 17 34 51",
            "0,24,HOUR,*/7,0 7 14 21",
            "1,32,DAY_OF_MONTH,*/13,1 14 27",
            "1,13,MONTH,*/3,1 4 7 10",
            "1,8,DAY_OF_WEEK,*/2,1 3 5 7"
    })
    void parseNumericFields(Integer valueFrom, Integer valueTo, CronField cronField, String step, String expectedEveryStep) {
        String allMinutes = prepareAllValues(valueFrom, valueTo);

        assertEquals(allMinutes, cut.parseField(cronField, "*"));
        assertEquals(allMinutes, cut.parseField(cronField, "0-1000"));
        assertEquals(expectedEveryStep, cut.parseField(cronField, step));
    }

    @Test
    void parseCmd() {
        String cmd = "*/5";
        assertEquals(cmd, cut.parseField(CronField.CMD, cmd));
    }

    private String prepareAllValues(int startInclusive, int endExclusive) {
        return  IntStream.range(startInclusive, endExclusive)
                .mapToObj(Integer::toString)
                .collect(Collectors.joining(" "));
    }
}