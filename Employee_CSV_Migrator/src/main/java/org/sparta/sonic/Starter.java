package org.sparta.sonic;

public class Starter {

    public static void start() {
        ReadCSV.connectToFile("src/main/resources/EmployeeRecordsMini.csv");
    }
}
