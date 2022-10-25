package org.sparta.sonic;

public class Starter {

    public static void start() {
        String[] x = ReadCSV.connectToFile("src/main/resources/EmployeeRecordsMini.csv");

        Employee[] array = ObjectEmployeeArrayCreator.objectEmployeeArrayCreator(x);

        //System.out.println(array[1].getEmail());
    }
}
