package org.sparta.sonic;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.Writer;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PerformanceSummaryTest {

    @Test
    void checkPerformanceForEveryThreadCountToMaxConnections() {
        HashMap<Integer, Long> perfTimes = new HashMap<>();

        int maxSQLConnections = 64;

        for (int i = 63; i < maxSQLConnections; i++) {
            long time = Starter.start(i);
            time = TimeUnit.NANOSECONDS.toMillis(time);
            perfTimes.put(i, time);
            System.out.printf("\nThreadCount: %d \ttime: %d ms", i, time);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
