package org.sparta.sonic;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class EmployeeDAOImpl implements EmployeeDAO {

    private final DBConnection db;

    EmployeeDAOImpl(DBConnection db) {
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
    public void insertEmployee(Employee employee) {
        try (PreparedStatement preparedStatement = db.connect().prepareStatement(PreparedSQL.INSERT_EMPLOYEE.getStatement())) {
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

            int changedRows = preparedStatement.executeUpdate();
            if (changedRows == 0) {
                System.out.println("Employee with id " + employee.getId() + " Could not be inserted"); // TODO LOG NO ROWS CHANGED
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertEmployees(ArrayList<Employee> employees) {
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

            int[] changedRows = preparedStatement.executeBatch();
            connection.commit();
            preparedStatement.close(); // statement is not closed automatically
            if (changedRows.length > 1) {
                System.out.println("Output of changed rows in database after insert: " + Arrays.toString(changedRows)); // TODO LOG NO ROWS CHANGED
            }

        } catch (SQLException e) {
            e.printStackTrace();
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
