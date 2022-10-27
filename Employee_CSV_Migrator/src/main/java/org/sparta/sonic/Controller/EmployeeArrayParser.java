package org.sparta.sonic.Controller;

import org.sparta.sonic.Model.Employee;
import org.sparta.sonic.Starter;
import org.sparta.sonic.utility.logging.LoggerSingleton;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmployeeArrayParser {
    private static final Logger logger = LoggerSingleton.getSingleton().getLogger();

    // Fields
    public ArrayList <Employee> validData;
    public ArrayList <Employee> corruptedData;

    private static HashSet <Integer> checkDuplicateID;
    private static HashSet <String>  checkDuplicateEmail;

    // Constructor
    public EmployeeArrayParser() {

        // Initialise Fields
        validData           = new ArrayList<>(Starter.getExpectedEmployeeCount());
        corruptedData       = new ArrayList<>(100);
        checkDuplicateID    = new HashSet<>();
        checkDuplicateEmail = new HashSet<>();
    }

    public void addEmployeesToArrayLists(String str) {

        // Initialise Object
        Employee employee = new Employee();

        // Set Object fields
        String[] fields = str.split(",");

        employee.setId(Integer.parseInt(fields[0]));
        employee.setNamePrefix(fields[1]);
        employee.setFirstName(fields[2]);
        employee.setMiddleInitial(fields[3]);
        employee.setLastName(fields[4]);
        employee.setGender(fields[5].charAt(0));
        employee.setEmail(fields[6]);
        employee.setDateOfBirth(Date.valueOf(formatTime(fields[7])));
        employee.setDateOfJoining(Date.valueOf(formatTime(fields[8])));
        employee.setSalary(Integer.parseInt(fields[9]));
        employee.setIsValid(checkIsValid(employee));

        // Add to ArrayList
        if(employee.getIsValid()) validData.add(employee);

        else corruptedData.add(employee);

    }

    private static boolean checkIsValid(Employee employee) {

        if(isDuplicates(employee))
        {
            //logger.log(Level.FINEST, "Duplicate");
            return false;
        }
        if(employee.getMiddleInitial().equals("FALSE")) {
            logger.log(Level.FINEST, "Middle initial");
            return false;
        }
        if(employee.getGender() == 'X') {
            logger.log(Level.FINEST, "Gender");
            return false;
        }
        if(employee.getSalary() <= 0 ) {
            logger.log(Level.FINEST, "Salary");
            return false;
        }
        if(employee.getDateOfBirth().after(Date.valueOf("2022-10-28"))) {
            logger.log(Level.FINEST, "DOB");
            return false;
        }
        if(employee.getDateOfJoining().after(Date.valueOf("2022-10-28")))
        {
            logger.log(Level.FINEST, "Date of joining"+employee.getDateOfJoining());
            return false;
        }
        return true;
    }

    private static boolean isDuplicates(Employee employee) {
        boolean checkID = checkDuplicateID.add(employee.getId());
        boolean checkEmail = checkDuplicateEmail.add(employee.getEmail());

        if(!checkEmail)
        {
            logger.log(Level.FINEST, "ID: "+employee.getId()+" Email duplicate: "+ employee.getEmail());
        }
        if(!checkID)
        {
            logger.log(Level.FINEST, "ID duplicate"+ employee.getId());
        }

        return !checkID || !checkEmail;
    }

    public static String formatTime(String str) {

        String[] strArray = str.split("/");
        return strArray[2] + "-" + strArray[0] + "-" + strArray[1];
    }
}
