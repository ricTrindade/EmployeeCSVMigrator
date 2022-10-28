package org.sparta.sonic.View;

import org.sparta.sonic.Model.Employee;
import org.sparta.sonic.utility.logging.LoggerSingleton;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

//Provide a simple user interface to display the results of reading the file –
// how many unique,
// clean records there are,
// how many duplicates,
// how many records with missing fields - is this necessary because csv doesn't contain missing fields
// possibly display the questionable records - unsure yet

public class DisplayManager {
    private static final Logger logger = LoggerSingleton.getSingleton().getLogger();
    //fields


    public static void printEmployees(ArrayList<Employee> employees) {
        for (Employee employee : employees) {
            logger.log(Level.FINER, "Printing Employee Array");
            printEmployee(employee);
            System.out.print("\n");
        }
    }

    public static void printEmployee(Employee employee) {
        Field[] fields = employee.getClass().getDeclaredFields();
        for (Field field : fields)
        {
            try {
                field.setAccessible(true);
                Object value = field.get(employee);
                if (!field.getName().equals("isValid")) {
                    System.out.println(field.getName()+": "+value);
                    logger.log(Level.FINEST, "Printing Employee");
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void getSummaryOfRecords(ArrayList<Employee> validData,ArrayList<Employee> corruptedData, int duplicateCounter) {
        int choice = 0;
        logger.log(Level.FINE, "Printing Summary");
        System.out.println("Summary of records below: ");
        System.out.println("Number of Clean Records: " + getEmployeeArraySize(validData));
        System.out.println("Number of Corrupted Records: " + getEmployeeArraySize(corruptedData));
        System.out.println("Of which are Duplications: " + duplicateCounter);
        System.out.println("Please enter 1 to see corrupted records, otherwise enter 2 to terminate");
        choice = Utils.scannedInteger();
        if(choice == 1){
            printEmployees(corruptedData);
        }
    }


    public static int getEmployeeArraySize(ArrayList<Employee> whichData) {
        return whichData.size();
    }

}
