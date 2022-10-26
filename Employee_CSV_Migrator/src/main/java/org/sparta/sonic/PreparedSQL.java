package org.sparta.sonic;

public enum PreparedSQL {
    DROP_EMPLOYEE_TABLE     ("DROP TABLE IF EXISTS employee"),
    CREATE_TABLE_EMPLOYEE   (
            "CREATE TABLE employee (" +
                    "empID int(6)" +
                    "" +
                    "" +
                    "" +
                    "" +
                    "" +
                    "" +
                    "" +
                    "" +
                    ")"
    ),
    INSERT_EMPLOYEE         ("INSERT INTO employee " +
                            "(empId, namePrefix, firstName, middleInitial, lastName, gender, email, dateOfBirth, dateOfJoining, salary)" +
                            "VALUES (?,?,?,?,?,?,?,?,?,?)"),
    SELECT_EMPLOYEES        ("SELECT * FROM employee"),
    SELECT_EMPLOYEE_BY_ID   ("SELECT * FROM employee WHERE empId=?");

    private final String statement;

    PreparedSQL(String statement) {
        this.statement = statement;
    }

    public String getStatement() {
        return statement;
    }
}
