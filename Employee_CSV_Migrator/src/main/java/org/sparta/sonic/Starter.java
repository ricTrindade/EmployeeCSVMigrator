package org.sparta.sonic;

import java.util.ArrayList;
import java.util.Arrays;

public class Starter {

    public static void start() {


        String[] x = ReadCSV.connectToFile("src/main/resources/EmployeeRecordsMini.csv", 11);
        ObjectEmployeeArrayCreator obj = new ObjectEmployeeArrayCreator(x);
        ArrayList <Employee> badList = obj.corruptedData;
        ArrayList <Employee> goodList = obj.validData;

        for(int i=0; i<goodList.size(); i++) {

            System.out.println(i);
            DisplayManager.printEmployee(goodList.get(i));
            System.out.println("");
        }

        //System.out.println(badList.size());
    }
}
