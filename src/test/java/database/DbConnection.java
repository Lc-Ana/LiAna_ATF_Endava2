package database;

import groovy.util.logging.Slf4j;

import java.sql.*;

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
        System.out.println("Db connection...");
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
            Class driver_class = Class.forName(driver);
            Driver driver = (Driver) driver_class.newInstance();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(url + dbName + password);
        } catch (Exception exception) {
            System.out.println(exception);
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

            String name;
            int id;
            String location;
            int age;
            while (resultSet.next()) {
                name = resultSet.getString("name");
                id = resultSet.getInt("id");
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
            e.getMessage();
        }
    }

    public static void main(String[] args) {
        DbConnection dbConnection1 = DbConnection.getInstance();
        dbConnection1.getData();
    }
}
