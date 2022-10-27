package org.sparta.sonic;

import org.junit.jupiter.api.*;
import org.sparta.sonic.Controller.ObjectEmployeeArrayCreator;
import org.sparta.sonic.Controller.db.DBConnection;
import org.sparta.sonic.Model.Employee;
import org.sparta.sonic.Model.dao.EmployeeDAO;
import org.sparta.sonic.Model.dao.EmployeeDAOImpl;
import org.sparta.sonic.Model.exception.EmployeeLoaderException;
import org.sparta.sonic.Model.factory.EmployeeDAOFactory;
import org.sparta.sonic.Model.factory.EmployeeFactory;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOTest {

    static EmployeeDAO employeeDAO;
    final static String testDatalocation = "/src/main/resources/EmployeeRecords.csv";

    @BeforeAll
    static void init() {
        DBConnection db = new DBConnection(
                "/src/main/resources/db.properties",
                "/src/main/resources/login.properties"
        );

        try {
            employeeDAO = EmployeeDAOFactory.generateEmployeeDAO("employee", db);
        } catch (EmployeeLoaderException e) {
            e.printStackTrace();
        }

    }

    @BeforeEach
    void setup() {

    }

    // test adding single employee
    @Test
    @DisplayName("Check that inserting one employee returns 1 row changed")
    void checkThatInsertEmployeeReturnsOne() {
        Employee employee = new Employee(
                999999,
                "Mrs.",
                "Serafina",
                "I",
                "Rojo",
                'F',
                "serafina.bumgarner@exxonmobil.com",
                Date.valueOf(ObjectEmployeeArrayCreator.formatTime("5/8/1967")),
                Date.valueOf(ObjectEmployeeArrayCreator.formatTime("6/4/2011")),
                69294
        );

        Assertions.assertEquals(1, employeeDAO.insertEmployee(employee, true));
    }


    @Test
    @DisplayName("Check that inserting three employees returns array of positive values")
    void checkThatInsertEmployeesReturnsPositiveArray() {
        Employee employee = new Employee(
                999999,
                "Mrs.",
                "Serafina",
                "I",
                "Rojo",
                'F',
                "serafina.bumgarner@exxonmobil.com",
                Date.valueOf(ObjectEmployeeArrayCreator.formatTime("5/8/1967")),
                Date.valueOf(ObjectEmployeeArrayCreator.formatTime("6/4/2011")),
                69294
        );

        Employee employee1 = new Employee(
                999998,
                "Mrs.",
                "Serafina",
                "I",
                "Rojo",
                'F',
                "serafina.bumgarner@exxonmobil.com",
                Date.valueOf(ObjectEmployeeArrayCreator.formatTime("5/8/1967")),
                Date.valueOf(ObjectEmployeeArrayCreator.formatTime("6/4/2011")),
                69294
        );

        Employee employee2 = new Employee(
                999997,
                "Mrs.",
                "Serafina",
                "I",
                "Rojo",
                'F',
                "serafina.bumgarner@exxonmobil.com",
                Date.valueOf(ObjectEmployeeArrayCreator.formatTime("5/8/1967")),
                Date.valueOf(ObjectEmployeeArrayCreator.formatTime("6/4/2011")),
                69294
        );
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(employee);
        employees.add(employee1);
        employees.add(employee2);

        Assertions.assertArrayEquals(new int[]{1,1,1}, employeeDAO.insertEmployees(employees, true));
    }






}
