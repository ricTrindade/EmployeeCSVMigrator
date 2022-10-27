package org.sparta.sonic.Controller;

import org.sparta.sonic.Model.Employee;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;

public class EmployeeArrayParser {

    // Fields
    public ArrayList <Employee> validData;
    public ArrayList <Employee> corruptedData;

    private static HashSet <Integer> checkDuplicateID;
    private static HashSet <String>  checkDuplicateEmail;

    // Constructor
    public EmployeeArrayParser() {

        // Initialise Fields
        validData           = new ArrayList<>();
        corruptedData       = new ArrayList<>();
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
