package org.sparta.sonic.Model.dao;

import org.sparta.sonic.Controller.db.DBConnection;
import org.sparta.sonic.Model.Employee;
import org.sparta.sonic.Model.PreparedSQL;
import org.sparta.sonic.Model.exception.EmployeeLoaderException;
import org.sparta.sonic.Model.exception.SQLRowNotFoundException;
import org.sparta.sonic.Model.factory.EmployeeFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class EmployeeDAOImpl implements EmployeeDAO {

    private final DBConnection db;

    public EmployeeDAOImpl(DBConnection db) {
        this.db = db;
    }


    @Override
    public ArrayList<Employee> selectAllEmployees() throws SQLRowNotFoundException {
        try (Statement statement = db.connect().createStatement()) {

            ResultSet resultSet = statement.executeQuery(PreparedSQL.SELECT_EMPLOYEES.getStatement());
            if(resultSet!=null) {
                ArrayList<Employee> employees = new ArrayList<>();
                while (resultSet.next()) {
                    employees.add(EmployeeFactory.generateEmployeeFromResultSet(resultSet));
                }
                return employees;
            } else {
                System.out.println("No data found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (EmployeeLoaderException e) {
            System.out.println(e.getMessage());
        }
        throw new SQLRowNotFoundException("No employee rows found");
    }

    @Override
    public Employee selectEmployeeById(int id) throws SQLRowNotFoundException {
        try (PreparedStatement preparedStatement = db.connect().prepareStatement(PreparedSQL.SELECT_EMPLOYEE_BY_ID.getStatement())) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet!=null) {
                resultSet.next();
                return EmployeeFactory.generateEmployeeFromResultSet(resultSet);
            } else {
                System.out.println("No data found");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (EmployeeLoaderException e) {
            System.out.println(e.getMessage());
        }
        throw new SQLRowNotFoundException("Could not find employee row with id: " + id);
    }

    @Override
    public int insertEmployee(Employee employee, boolean test) {
        int changedRows = 0;
        Savepoint savePoint = null;
        try (Connection connection = db.connect()) {
            connection.setAutoCommit(false);
            if(test) {
                savePoint = connection.setSavepoint();
            }
            PreparedStatement preparedStatement = connection.prepareStatement(PreparedSQL.INSERT_EMPLOYEE.getStatement());


            preparedStatement.setInt(1,     employee.getId());
            preparedStatement.setString(2,  employee.getNamePrefix());
            preparedStatement.setString(3,  employee.getFirstName());
            preparedStatement.setString(4,  employee.getMiddleInitial());
            preparedStatement.setString(5,  employee.getLastName());
            preparedStatement.setString(6,  Character.toString(employee.getGender()));
            preparedStatement.setString(7,  employee.getEmail());
            preparedStatement.setDate(8,    employee.getDateOfBirth());
            preparedStatement.setDate(9,    employee.getDateOfJoining());
            preparedStatement.setInt(10,    employee.getSalary());

            changedRows = preparedStatement.executeUpdate();

            if(test) {
                connection.rollback(savePoint);
            } else {
                connection.commit();
            }

            if (changedRows == 0) {
                System.out.println("Employee with id " + employee.getId() + " Could not be inserted"); // TODO LOG NO ROWS CHANGED
            }

            preparedStatement.close(); // statement is not closed automatically
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return changedRows;
    }

    @Override
    public int[] insertEmployees(ArrayList<Employee> employees, boolean test) {
        synchronized (this) {
            int[] changedRows = new int[0];
            Savepoint savePoint = null;
            try (Connection connection = db.connect()) {
                connection.setAutoCommit(false);
                if(test) { savePoint = connection.setSavepoint();}
                PreparedStatement preparedStatement = connection.prepareStatement(PreparedSQL.INSERT_EMPLOYEE.getStatement());

                for (Employee employee : employees) {
                    preparedStatement.setInt(1,     employee.getId());
                    preparedStatement.setString(2,  employee.getNamePrefix());
                    preparedStatement.setString(3,  employee.getFirstName());
                    preparedStatement.setString(4,  employee.getMiddleInitial());
                    preparedStatement.setString(5,  employee.getLastName());
                    preparedStatement.setString(6,  Character.toString(employee.getGender()));
                    preparedStatement.setString(7,  employee.getEmail());
                    preparedStatement.setDate(8,    employee.getDateOfBirth());
                    preparedStatement.setDate(9,    employee.getDateOfJoining());
                    preparedStatement.setInt(10,    employee.getSalary());
                    preparedStatement.addBatch();
                }

                changedRows = preparedStatement.executeBatch();
                if(test) {
                    connection.rollback(savePoint);
                } else {
                    connection.commit();
                }

                if (changedRows.length > 1) {
                    System.out.println("Output of changed rows in database after insert: " + Arrays.toString(changedRows)); // TODO LOG NO ROWS CHANGED
                }

                preparedStatement.close(); // statement is not closed automatically
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return changedRows;
        }
    }

    public int[] insertEmployees(ArrayList<Employee> employees) {
        synchronized (this) {
            int[] changedRows = new int[0];
            Savepoint savePoint = null;
            try (Connection connection = db.connect()) {
                connection.setAutoCommit(false);

                PreparedStatement preparedStatement = connection.prepareStatement(PreparedSQL.INSERT_EMPLOYEE.getStatement());

                for (Employee employee : employees) {
                    preparedStatement.setInt(1,     employee.getId());
                    preparedStatement.setString(2,  employee.getNamePrefix());
                    preparedStatement.setString(3,  employee.getFirstName());
                    preparedStatement.setString(4,  employee.getMiddleInitial());
                    preparedStatement.setString(5,  employee.getLastName());
                    preparedStatement.setString(6,  Character.toString(employee.getGender()));
                    preparedStatement.setString(7,  employee.getEmail());
                    preparedStatement.setDate(8,    employee.getDateOfBirth());
                    preparedStatement.setDate(9,    employee.getDateOfJoining());
                    preparedStatement.setInt(10,    employee.getSalary());
                    preparedStatement.addBatch();
                }

                changedRows = preparedStatement.executeBatch();
                connection.commit();

                if (changedRows.length > 1) {
                    System.out.println("Output of changed rows in database after insert: " + Arrays.toString(changedRows)); // TODO LOG NO ROWS CHANGED
                }

                preparedStatement.close(); // statement is not closed automatically
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return changedRows;
        }

    }

    public int[] insertEmployees(ArrayList<Employee> employees,int startIndex, int endIndex) {

            int[] changedRows = new int[0];
            Savepoint savePoint = null;
            try (Connection connection = db.connect()) {
                connection.setAutoCommit(false);

                PreparedStatement preparedStatement = connection.prepareStatement(PreparedSQL.INSERT_EMPLOYEE.getStatement());

                for (int i = startIndex;i<endIndex;i++) {

                    preparedStatement.setInt(1, employees.get(i).getId());
                    preparedStatement.setString(2,  employees.get(i).getNamePrefix());
                    preparedStatement.setString(3,  employees.get(i).getFirstName());
                    preparedStatement.setString(4,  employees.get(i).getMiddleInitial());
                    preparedStatement.setString(5,  employees.get(i).getLastName());
                    preparedStatement.setString(6,  Character.toString(employees.get(i).getGender()));
                    preparedStatement.setString(7,  employees.get(i).getEmail());
                    preparedStatement.setDate(8,    employees.get(i).getDateOfBirth());
                    preparedStatement.setDate(9,    employees.get(i).getDateOfJoining());
                    preparedStatement.setInt(10,    employees.get(i).getSalary());
                    preparedStatement.addBatch();
                }

                changedRows = preparedStatement.executeBatch();
                connection.commit();

                if (changedRows.length > 1) {
                    System.out.println("Output of changed rows in database after insert: " + Arrays.toString(changedRows)); // TODO LOG NO ROWS CHANGED
                }

                preparedStatement.close(); // statement is not closed automatically
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return changedRows;

    }

    @Override
    public void insertEmployeesConcurrent(ArrayList<Employee> employees) {
        int threadCount = 14;
        int sizePerArray = employees.size()/threadCount;
        int remainder = employees.size()%threadCount;
        int remainderStart = 0;


        //array list of 100
        //thread 1: 0-50
        //thread 2: 50-100

        Thread[] threadArray = new Thread[threadCount];
        for (int i = 0;i<threadCount;i++){
            int finalI = i;
            threadArray[i] = new Thread(){
                public void run() {

                    int[] ints = insertEmployees(employees, finalI * sizePerArray
                            , (finalI + 1) * sizePerArray);
                    System.out.println("Thread: "+finalI);
                }
            };
            threadArray[i].start();

            if(i==threadCount-1)
            {
                remainderStart = (finalI + 1) * sizePerArray;
            }
        }
        int finalRemainderStart = remainderStart;
        int finalRemainderStart1 = remainderStart;
        Thread lastThread = new Thread(){
            public void run() {

                int[] ints = insertEmployees(employees, finalRemainderStart
                        , finalRemainderStart1 +remainder);
                System.out.println("FINAL THREAD");
            }
        };
        lastThread.start();

        for (int i = 0;i<threadCount;i++){
            try {
                threadArray[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            lastThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }





    @Override
    public void dropEmployeeTable() {
        try (Statement statement = db.connect().createStatement()) {
            statement.executeUpdate(PreparedSQL.DROP_EMPLOYEE_TABLE.getStatement());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createEmployeeTable() {
        try (Statement statement = db.connect().createStatement()) {
            statement.executeUpdate(PreparedSQL.CREATE_EMPLOYEE_TABLE.getStatement());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
