package org.sparta.sonic;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class EmployeeDAOImpl implements EmployeeDAO {

    private DBConnection db=null;

    private static final String SELECT_PERSONS="SELECT * FROM persons";
    private static final String SELECT_PERSON_BY_ID="SELECT firstName FROM persons WHERE personId=?";
    private static final String UPDATE_PERSONS="UPDATE persons SET lastName=? WHERE personId=? AND lastName!=?"; // !=? means not equal to ?

    EmployeeDAOImpl(DBConnection db) {
        this.db = db;
    }

    public void selectAllPersons() {
        try (Statement statement = db.connect().createStatement()) {

            ResultSet resultSet = statement.executeQuery(SELECT_PERSONS);
            if(resultSet!=null) {
                while (resultSet.next()) {
                    System.out.printf("PersonID: " + resultSet.getInt(1));
                    System.out.printf(" Title: " + resultSet.getString(2));
                    System.out.printf(" Firstname: " + resultSet.getString(3));
                    System.out.printf(" Lastname: " + resultSet.getString(4));
                }
            } else {
                System.out.println("No data found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void selectPersonsByID(int personID) {
        try (PreparedStatement preparedStatement = db.connect().prepareStatement(SELECT_PERSON_BY_ID)) {
            preparedStatement.setInt(1, personID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet!=null) {
                while (resultSet.next()) {
                    System.out.println("First name of person with id " + personID + " is::" + resultSet.getString(1));
                }
            } else {
                System.out.println("No data found");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updatePersonsByID(int personID, String updateLastName) {
        try (PreparedStatement preparedStatement = db.connect().prepareStatement(UPDATE_PERSONS)) {
            preparedStatement.setString(1, updateLastName);
            preparedStatement.setInt(2, personID);
            preparedStatement.setString(3, updateLastName);
            int changedRows = preparedStatement.executeUpdate();
            if (changedRows != 0) {
                System.out.println("The lastName " + updateLastName + " of id " + personID + " has been updated");
            } else {
                System.out.println("The lastName " + updateLastName + " of id " + personID + " has NOT been updated");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
