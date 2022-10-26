package org.sparta.sonic;

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
