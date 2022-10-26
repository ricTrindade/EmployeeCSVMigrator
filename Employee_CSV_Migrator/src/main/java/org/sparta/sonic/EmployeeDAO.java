package org.sparta.sonic;

import java.util.ArrayList;

public interface EmployeeDAO {

    ArrayList<Employee> selectAllEmployees() throws SQLRowNotFoundException;

    Employee selectEmployeeById(final int id) throws SQLRowNotFoundException;

    void insertEmployee(Employee employee);

    void insertEmployees(ArrayList<Employee> employees);

    void dropEmployeeTable();

    void createEmployeeTable();

}
