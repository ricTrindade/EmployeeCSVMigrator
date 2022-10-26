package org.sparta.sonic;

import org.sparta.sonic.Controller.ObjectEmployeeArrayCreator;
import org.sparta.sonic.Controller.ReadCSV;
import org.sparta.sonic.Model.Employee;

public class Starter {

    public static void start() {

        String[] x = ReadCSV.connectToFile("src/main/resources/EmployeeRecordsMini.csv", 11);
        ObjectEmployeeArrayCreator obj = new ObjectEmployeeArrayCreator(x);
        Employee Mark = obj.validData.get(0);
        System.out.println(Mark.getId());
    }
}
