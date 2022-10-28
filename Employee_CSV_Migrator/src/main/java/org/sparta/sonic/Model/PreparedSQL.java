package org.sparta.sonic.Model;

public enum PreparedSQL {
    DROP_EMPLOYEE_TABLE     ("DROP TABLE IF EXISTS employee"),
    CREATE_EMPLOYEE_TABLE   (
            "CREATE TABLE employee ("          +
                    "id MEDIUMINT"                       + ", " +
                    "name_prefix VARCHAR(5)"             + ", " +
                    "first_name VARCHAR(30)"             + ", " +
                    "middle_initial VARCHAR(1)"          + ", " +
                    "last_name VARCHAR(30)"              + ", " +
                    "gender CHAR(1)"                     + ", " +
                    "email VARCHAR(256)"                 + ", " +
                    "date_of_birth DATETIME"             + ", " +
                    "date_of_joining DATETIME"           + ", " +
                    "salary INT"                         + ", " +
                    "PRIMARY KEY (id)"                   +
                    ")"
    ),
    INSERT_EMPLOYEE         ("INSERT INTO employee " +
                            "(id, name_prefix, first_name, middle_initial, last_name, gender, email, date_of_birth, date_of_joining, salary)" +
                            "VALUES (?,?,?,?,?,?,?,?,?,?)"),
    SELECT_EMPLOYEES        ("SELECT * FROM employee"),
    SELECT_EMPLOYEE_BY_ID   ("SELECT * FROM employee WHERE id=?");

    private final String statement;

    PreparedSQL(String statement) {
        this.statement = statement;
    }

    public String getStatement() {
        return statement;
    }
}
