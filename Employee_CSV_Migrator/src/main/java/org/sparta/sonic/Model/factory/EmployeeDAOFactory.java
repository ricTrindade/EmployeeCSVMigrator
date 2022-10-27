package org.sparta.sonic.Model.factory;

import org.sparta.sonic.Controller.db.DBConnection;
import org.sparta.sonic.Model.dao.EmployeeDAO;
import org.sparta.sonic.Model.dao.EmployeeDAOImpl;
import org.sparta.sonic.Model.exception.EmployeeLoaderException;

public class EmployeeDAOFactory {
    public static EmployeeDAO generateEmployeeDAO(String daoChoice, DBConnection db) throws EmployeeLoaderException {
        switch (daoChoice) {
            case "employee" -> {
                return new EmployeeDAOImpl(db);
            }
            default -> throw new EmployeeLoaderException("Invalid employee choice: " + daoChoice);
        }
    }
}
