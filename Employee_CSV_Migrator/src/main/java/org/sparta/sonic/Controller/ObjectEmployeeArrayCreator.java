package org.sparta.sonic.Controller;

import org.sparta.sonic.Model.Employee;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;

public class ObjectEmployeeArrayCreator {

    // Fields
    public ArrayList <Employee> validData;
    public ArrayList <Employee> corruptedData;

    private static HashSet <Integer> checkDuplicateID;
    private static HashSet <String>  checkDuplicateEmail;

    // Constructor
    public ObjectEmployeeArrayCreator(String[] array) {

        // Initialise Fields
        validData           = new ArrayList<>();
        corruptedData       = new ArrayList<>();
        checkDuplicateID    = new HashSet<>();
        checkDuplicateEmail = new HashSet<>();

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

        if(isDuplicates(employee)) return false;
        if(employee.getMiddleInitial().equals("FALSE")) return false;
        if(employee.getGender() == 'X') return false;
        if(employee.getSalary() <= 0 ) return false;
        if(employee.getDateOfBirth().after(Date.valueOf("2022-10-28"))) return false;
        return !employee.getDateOfJoining().after(Date.valueOf("2022-10-28"));
    }

    private static boolean isDuplicates(Employee employee) {

        boolean checkID = checkDuplicateID.add(employee.getId());
        boolean checkEmail = checkDuplicateEmail.add(employee.getEmail());

        return !checkID || !checkEmail;
    }

    private static String formatTime(String str) {

        String[] strArray = str.split("/");
        return strArray[2] + "-" + strArray[0] + "-" + strArray[1];
    }
}
