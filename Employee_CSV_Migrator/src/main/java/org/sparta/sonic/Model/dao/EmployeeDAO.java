package org.sparta.sonic.Model.dao;

import org.sparta.sonic.Model.Employee;
import org.sparta.sonic.Model.exception.SQLRowNotFoundException;

import java.util.ArrayList;

public interface EmployeeDAO {

    ArrayList<Employee> selectAllEmployees() throws SQLRowNotFoundException;

    Employee selectEmployeeById(final int id) throws SQLRowNotFoundException;

    int insertEmployee(Employee employee, boolean test);

    int[] insertEmployees(ArrayList<Employee> employees, boolean test);

    void dropEmployeeTable();

    void createEmployeeTable();

    void insertEmployeesConcurrent(ArrayList<Employee> employees);

    int[] insertEmployees(ArrayList<Employee> employees);

    int[] insertEmployees(ArrayList<Employee> employees, int start, int end);
}
