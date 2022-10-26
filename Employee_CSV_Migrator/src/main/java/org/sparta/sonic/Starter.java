package org.sparta.sonic;


import org.sparta.sonic.Controller.ObjectEmployeeArrayCreator;
import org.sparta.sonic.Controller.ReadCSV;
import org.sparta.sonic.Controller.db.DBConnection;
import org.sparta.sonic.Model.Employee;
import org.sparta.sonic.Model.dao.EmployeeDAO;
import org.sparta.sonic.Model.factory.EmployeeDAOFactory;
import org.sparta.sonic.Model.exception.EmployeeLoaderException;
import org.sparta.sonic.View.DisplayManager;

import java.util.ArrayList;


public class Starter {

    public static void start() {
        DBConnection db = new DBConnection(
                "/src/main/resources/db.properties",
                "/src/main/resources/login.properties"
        );

        try {
            EmployeeDAO employeeDAO = EmployeeDAOFactory.generateEmployeeDAO("employee", db);
        } catch (EmployeeLoaderException e) {
            e.printStackTrace();
        }


        String[] x = ReadCSV.connectToFile("/src/main/resources/EmployeeRecordsMini.csv", 11);
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
