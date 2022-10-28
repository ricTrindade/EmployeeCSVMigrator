package org.sparta.sonic;


import org.sparta.sonic.Controller.EmployeeArrayParser;
import org.sparta.sonic.Controller.ReadCSV;
import org.sparta.sonic.Controller.db.DBConnection;
import org.sparta.sonic.Model.dao.EmployeeDAO;
import org.sparta.sonic.Model.dao.EmployeeDAOImpl;
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

        EmployeeArrayParser employeeParser = ReadCSV.connectToFile("src/main/resources/EmployeeRecordsLarge.csv");

        if (employeeDAO != null) {
            logger.log(Level.FINE, "Creating and Dropping database");
            employeeDAO.dropEmployeeTable();
            employeeDAO.createEmployeeTable();


            //employeeDAO.insertEmployees(employeeParser.validData, false);
            employeeDAO.insertEmployeesConcurrent(employeeParser.validData);

            System.out.println("Time taken to insert: "+ (System.nanoTime()-startTime) / 1000000000F + " seconds");
            //DisplayManager.getSummaryOfRecords(employeeParser.validData, employeeParser.corruptedData, employeeParser.duplicateCounter);


        } else {
            logger.log(Level.INFO, "employeeDAO was not instantiated");
        }
        //System.out.println(badList.size());
    }

    public static long start(int threadCount) {
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

        EmployeeDAOImpl.threadCount = threadCount;


        EmployeeArrayParser employeeParser = ReadCSV.connectToFile("src/main/resources/EmployeeRecordsLarge.csv");

        if (employeeDAO != null) {
            employeeDAO.dropEmployeeTable();
            employeeDAO.createEmployeeTable();



            //employeeDAO.insertEmployees(employeeParser.validData, false);
            employeeDAO.insertEmployeesConcurrent(employeeParser.validData);

            return System.nanoTime() - startTime;

        } else {
            logger.log(Level.INFO, "employeeDAO was not instantiated");
        }

        return 0;
    }

    public static int getExpectedEmployeeCount() {
        return expectedEmployeeCount;
    }

    public static void setExpectedEmployeeCount(int expectedEmployeeCount) {
        Starter.expectedEmployeeCount = expectedEmployeeCount;
    }
}
