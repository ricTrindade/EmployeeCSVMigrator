package org.sparta.sonic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.sparta.sonic.Controller.EmployeeArrayParser;
import org.sparta.sonic.Controller.ReadCSV;
import org.sparta.sonic.View.DisplayManager;

public class EmployeeArrayParserTest {
    EmployeeArrayParser employeeArrayParser = ReadCSV.connectToFile("src/main/resources/EmployeeRecordsMini.csv");

    @Test
    @DisplayName("Check that class has parsed CSV")
    void checkThatArrayHasBeenParsed()
    {
        //displaymanager output lists
    }
}
