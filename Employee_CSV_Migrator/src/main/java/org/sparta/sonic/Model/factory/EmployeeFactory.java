package org.sparta.sonic.Model.factory;

import org.sparta.sonic.Model.Employee;
import org.sparta.sonic.Model.exception.EmployeeLoaderException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeFactory {
    public static Employee generateEmployeeFromResultSet(ResultSet set) throws EmployeeLoaderException {
        try {
            return new Employee(
                    set.getInt(1),
                    set.getString(2),
                    set.getString(3),
                    set.getString(4),
                    set.getString(5),
                    set.getString(6).charAt(0),
                    set.getString(7),
                    set.getDate(8),
                    set.getDate(9),
                    set.getInt(10)
            );
        } catch (SQLException e) {
            throw new EmployeeLoaderException("Could not create employee from resultset: " + set.toString());
        }
    }
}
