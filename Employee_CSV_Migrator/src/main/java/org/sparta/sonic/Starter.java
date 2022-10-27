package org.sparta.sonic;


import org.sparta.sonic.Controller.EmployeeArrayParser;
import org.sparta.sonic.Controller.ReadCSV;
import org.sparta.sonic.Controller.db.DBConnection;
import org.sparta.sonic.Model.dao.EmployeeDAO;
import org.sparta.sonic.Model.exception.SQLRowNotFoundException;
import org.sparta.sonic.Model.factory.EmployeeDAOFactory;
import org.sparta.sonic.Model.exception.EmployeeLoaderException;
import org.sparta.sonic.View.DisplayManager;
import org.sparta.sonic.utility.logging.LoggerSingleton;

import java.util.logging.Level;
import java.util.logging.Logger;


public class Starter {

    private static final Logger logger = LoggerSingleton.getSingleton().getLogger();
    private static int expectedEmployeeCount = 100;


    public static void start() {


        DBConnection db = new DBConnection(
                "/src/main/resources/db.properties",
                "/src/main/resources/login.properties"
        );

        EmployeeDAO employeeDAO = null;

        try {
            employeeDAO = EmployeeDAOFactory.generateEmployeeDAO("employee", db);
        } catch (EmployeeLoaderException e) {
            e.printStackTrace();
        }



        EmployeeArrayParser employeeParser = ReadCSV.connectToFile("src/main/resources/EmployeeRecordsLarge.csv");

        if (employeeDAO != null) {
            employeeDAO.dropEmployeeTable();
            employeeDAO.createEmployeeTable();



            long startTime = System.nanoTime();
            //employeeDAO.insertEmployees(employeeParser.validData, false);
            employeeDAO.insertEmployeesConcurrent(employeeParser.validData);

            System.out.println("Time taken to insert: "+ (System.nanoTime()-startTime) / 1000000000F + " seconds");


            /*
            try {
                DisplayManager.printEmployees(employeeDAO.selectAllEmployees());
            } catch (SQLRowNotFoundException e) {
                throw new RuntimeException(e);
            }

             */

        } else {
            logger.log(Level.INFO, "employeeDAO was not instantiated");
        }
        //System.out.println(badList.size());
    }

    public static int getExpectedEmployeeCount() {
        return expectedEmployeeCount;
    }

    public static void setExpectedEmployeeCount(int expectedEmployeeCount) {
        Starter.expectedEmployeeCount = expectedEmployeeCount;
    }
}
