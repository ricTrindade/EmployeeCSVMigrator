package org.sparta.sonic;

import java.util.ArrayList;
import java.util.Arrays;

public class Starter {

    public static void start() {


        String[] x = ReadCSV.connectToFile("src/main/resources/EmployeeRecordsMini.csv", 11);

        ObjectEmployeeArrayCreator obj = new ObjectEmployeeArrayCreator(x);

        ArrayList <Employee> badList = obj.corruptedData;

        System.out.println(badList.size());
    }
}
