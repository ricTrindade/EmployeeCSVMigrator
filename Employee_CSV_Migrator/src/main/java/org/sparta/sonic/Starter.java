package org.sparta.sonic;


import org.sparta.sonic.Controller.EmployeeArrayParser;
import org.sparta.sonic.Controller.ReadCSV;
import org.sparta.sonic.Controller.db.DBConnection;
import org.sparta.sonic.Model.dao.EmployeeDAO;
import org.sparta.sonic.Model.factory.EmployeeDAOFactory;
import org.sparta.sonic.Model.exception.EmployeeLoaderException;
import org.sparta.sonic.View.DisplayManager;


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



        EmployeeArrayParser obj = ReadCSV.connectToFile("src/main/resources/EmployeeRecordsMini.csv", 11);


        for(int i=0; i<obj.validData.size(); i++) {

            System.out.println(i);
            DisplayManager.printEmployee(obj.validData.get(i));
            System.out.println("");
        }

        //System.out.println(badList.size());
    }
}
