package org.sparta.sonic;

import java.sql.Date;
import java.util.ArrayList;

public class ObjectEmployeeArrayCreator {

    // Fields
    public ArrayList <Employee> validData;
    public ArrayList <Employee> corruptedData;

    // Constructor
    public ObjectEmployeeArrayCreator(String[] array) {

        // Initialise Fields
        validData     = new ArrayList<>();
        corruptedData = new ArrayList<>();

        // Initialise Array
        Employee[] employees = new Employee[array.length-1];

        for(int i=0; i<employees.length; i++) {

            // Initialise Object
            employees[i] = new Employee();

            // Set Object fields
            String[] fields = array[i+1].split(",");
            employees[i].setId(Integer.parseInt(fields[0]));
            employees[i].setNamePrefix(fields[1]);
            employees[i].setFirstName(fields[2]);
            employees[i].setMiddleInitial(fields[3]);
            employees[i].setLastName(fields[4]);
            employees[i].setGender(fields[5].charAt(0));
            employees[i].setEmail(fields[6]);
            employees[i].setDateOfBirth(Date.valueOf(formatTime(fields[7])));
            employees[i].setDateOfJoining(Date.valueOf(formatTime(fields[8])));
            employees[i].setSalary(Integer.parseInt(fields[9]));
            employees[i].setIsValid(checkIsValid(employees[i]));

            // Add to ArrayList
            if(employees[i].getIsValid()) validData.add(employees[i]);
            else corruptedData.add(employees[i]);
        }
    }

    private static boolean checkIsValid(Employee employee) {

        return true;
    }

    private static String formatTime(String str) {

        String[] strArray = str.split("/");
        return strArray[2] + "-" + strArray[0] + "-" + strArray[1];
    }
}
