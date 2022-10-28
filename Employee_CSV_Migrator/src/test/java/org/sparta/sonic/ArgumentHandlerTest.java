package org.sparta.sonic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ArgumentHandlerTest {
    @Test
    @DisplayName("Check argumentHandler(211) returns 211")
    void checkArgumentHandlerReturn()
    {
        String[] inputString = new String[]{"211"};
        int testCount = ArgumentHandler.getRowCount(inputString);
        Assertions.assertEquals(211, testCount);
    }

    @Test
    @DisplayName("Check argumentHandler() returns default 65000")
    void checkEmptyArgumentHandlerReturn65000()
    {
        String[] inputString = new String[]{};
        int testCount = ArgumentHandler.getRowCount(inputString);
        Assertions.assertEquals(65000, testCount);
    }

    @Test
    @DisplayName("Check argumentHandler(-10) returns default 65000")
    void checkNegativeArgumentHandlerReturnDefault()
    {
        String[] inputString = new String[]{"-10"};
        int testCount = ArgumentHandler.getRowCount(inputString);
        Assertions.assertEquals(65000, testCount);
    }

}
