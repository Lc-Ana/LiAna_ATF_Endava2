package edu.endava.upskilling.testing.database;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@Slf4j
public class DbConnection {

    String url = "jdbc:mysql://localhost:3306/";
    String dbName = "Db1";
    String driver = "com.mysql.cj.jdbc.Driver";
    String userName = "root";
    String password = "february2024!";

    private static DbConnection dbConnection;
    private final Connection connection;

    private DbConnection() {
        System.out.println("Db connection is starting...");
        connection = createConnection();
    }

    public static DbConnection getInstance() {
        if (dbConnection == null) {
            dbConnection = new DbConnection();
        }
        return dbConnection;
    }

    public Connection createConnection() {
        Connection connection = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(
                    url.concat(dbName), userName, password);
        } catch (Exception exception) {
            log.info("Getting an error creating db connection");
            log.info(exception.getMessage());
        }
        return connection;
    }

    public void getData() {
        try {
            Statement statement;
            statement = connection.createStatement();
            ResultSet resultSet;
            resultSet = statement.executeQuery(
                    "select * from Employeeinfo");

            String name, location;
            int id, age;

            System.out.println("Retrieved data -->");
            while (resultSet.next()) {
                id = resultSet.getInt("id");
                name = resultSet.getString("name");
                location = resultSet.getString("location").trim();
                age = resultSet.getInt("age");
                System.out.println("Name:" + name
                        + " Id:" + id
                        + " Location:" + location
                        + " Age:" + age);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            log.info("Getting an error to retrieve data");
            log.info(e.getMessage());
        }
    }

    public static void main(String[] args) {
        DbConnection dbConnection1 = DbConnection.getInstance();
        dbConnection1.getData();
    }
}
