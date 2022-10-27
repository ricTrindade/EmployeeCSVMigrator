package org.sparta.sonic;


import org.sparta.sonic.Controller.EmployeeArrayParser;
import org.sparta.sonic.Controller.ReadCSV;
import org.sparta.sonic.View.DisplayManager;


public class Starter {

    public static void start() {


        EmployeeArrayParser obj = ReadCSV.connectToFile("src/main/resources/EmployeeRecordsMini.csv", 11);

        for(int i=0; i<obj.validData.size(); i++) {

            System.out.println(i);
            DisplayManager.printEmployee(obj.validData.get(i));
            System.out.println("");
        }

        //System.out.println(badList.size());
    }
}
