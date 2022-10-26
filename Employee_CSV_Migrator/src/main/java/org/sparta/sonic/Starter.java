package org.sparta.sonic;


import org.sparta.sonic.Controller.ObjectEmployeeArrayCreator;
import org.sparta.sonic.Controller.ReadCSV;
import org.sparta.sonic.Model.Employee;
import org.sparta.sonic.View.DisplayManager;

import java.util.ArrayList;


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
