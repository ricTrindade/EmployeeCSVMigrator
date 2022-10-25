package org.sparta.sonic;

import java.sql.Date;

public class ObjectEmployeeArrayCreator {

    public static Employee[] objectEmployeeArrayCreator(String[] array) {

        Employee[] employees = new Employee[array.length];

        for(int i=0; i<array.length; i++) {

            employees[i] = new Employee();
            String[] fields = array[i].split(",");
            employees[i].setId(Integer.parseInt(fields[0]));
            employees[i].setNamePrefix(fields[1]);
            employees[i].setFirstName(fields[2]);
            employees[i].setMiddleInitial(fields[3]);
            employees[i].setLastName(fields[4]);
            employees[i].setGender(fields[5].charAt(0));
            employees[i].setEmail(fields[6]);
            //employees[i].setDateOfBirth(Date.valueOf(fields[7]));
            //employees[i].setDateOfJoining(Date.valueOf(fields[8]));
            employees[i].setSalary(Integer.parseInt(fields[9]));
        }
        return employees;
    }
}
