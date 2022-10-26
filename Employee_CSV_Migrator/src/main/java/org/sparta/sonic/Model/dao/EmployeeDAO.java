package org.sparta.sonic.Model.dao;

import org.sparta.sonic.Model.Employee;
import org.sparta.sonic.Model.exception.SQLRowNotFoundException;

import java.util.ArrayList;

public interface EmployeeDAO {

    ArrayList<Employee> selectAllEmployees() throws SQLRowNotFoundException;

    Employee selectEmployeeById(final int id) throws SQLRowNotFoundException;

    void insertEmployee(Employee employee);

    void insertEmployees(ArrayList<Employee> employees);

    void dropEmployeeTable();

    void createEmployeeTable();

}
