package com.mrucki.cron.parser;

import com.mrucki.cron.parser.CronField;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CronFieldTest {

    @Test
    void calculateLongestLabel() {
        assertEquals(12, CronField.calculateLongestLabel());
    }
}