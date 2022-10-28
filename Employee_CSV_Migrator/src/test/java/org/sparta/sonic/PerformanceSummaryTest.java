package org.sparta.sonic;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.Writer;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

public class PerformanceSummaryTest {

    @Test
    void checkPerformanceForEveryThreadCountToMaxConnections() {
        HashMap<Integer, Long> perfTimes = new HashMap<>();

        int maxSQLConnections = 4;

        for (int i = 1; i < maxSQLConnections; i++) {

            System.out.printf("\nThreadCount: %d \ttime: %d ns", i, Starter.start(i));
        }

    }
}
