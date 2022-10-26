package org.sparta.sonic;

public class Starter {

    public static void start() {
        DBConnection db = new DBConnection(
                "/src/main/resources/db.properties",
                "/src/main/resources/login.properties"
        );

        try {
            EmployeeDAO employeeDAO = EmployeeDAOFactory.generateEmployee("employee", db);
        } catch (EmployeeLoaderException e) {
            e.printStackTrace();
        }

    }
}
