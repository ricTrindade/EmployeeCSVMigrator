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

    public static void start() {
        long startTime = System.nanoTime();

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


        EmployeeArrayParser employeeParser = ReadCSV.connectToFile("src/main/resources/EmployeeRecordsLarge.csv", 65501);

        if (employeeDAO != null) {
            employeeDAO.dropEmployeeTable();
            employeeDAO.createEmployeeTable();

            employeeDAO.insertEmployees(employeeParser.validData, false);
            System.out.println("Time taken: "+ (System.nanoTime()-startTime) / 1000000000F + " seconds");
            System.out.println(employeeParser.validData.size());

            /*
            try {
                DisplayManager.printEmployees(employeeDAO.selectAllEmployees());
            } catch (SQLRowNotFoundException e) {
                throw new RuntimeException(e);
            }

             */


            //DisplayManager.printEmployees(employeeParser.corruptedData);
        } else {
            logger.log(Level.INFO, "employeeDAO was not instantiated");
        }
        //System.out.println(badList.size());
    }
}
