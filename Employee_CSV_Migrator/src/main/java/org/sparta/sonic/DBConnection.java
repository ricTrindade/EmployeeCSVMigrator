package org.sparta.sonic;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    private Connection connection=null;

    private String URL="";

    private Properties credentials = new Properties();

    Properties dbConfig = new Properties();

    DBConnection(String dbConfigLocation, String credentialsLocation) {

        try {
            dbConfig.load(new FileReader(System.getProperty("user.dir") + dbConfigLocation));
            credentials.load(new FileReader(System.getProperty("user.dir") + credentialsLocation));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String driver = dbConfig.getProperty("driver");
        String ip = dbConfig.getProperty("ip");
        String port = dbConfig.getProperty("port");
        String dbName = dbConfig.getProperty("dbName");

        URL = driver + ip + ":" + port + "/" + dbName + "?serverTimezone=GMT";
    }

    public Connection connect() {
        try {
            connection= DriverManager.getConnection(URL, credentials.getProperty("username"), credentials.getProperty("password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
