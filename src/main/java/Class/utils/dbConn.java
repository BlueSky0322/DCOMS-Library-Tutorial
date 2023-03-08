/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author handikaharianto
 */
public class dbConn {

    private final static String DATABASE_URL = "jdbc:derby://localhost:1527/Library";
    private final static String DATABASE_USERNAME = "dcoms";
    private final static String DATABASE_PASSWORD = "dcoms";
    private static Connection dbConnection;

    public static void connectDB() {
        try {
            dbConnection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            dbConnection.setAutoCommit(false);
            System.out.println("Database is running...");

            //initialise all tables
            initialiseTable(createStatement());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static Statement createStatement() throws SQLException {
        return dbConnection.createStatement();
    }

    public static PreparedStatement preparedStatement(String query) throws SQLException {
        return dbConnection.prepareStatement(query);
    }

    public static void commit() throws SQLException {
        dbConnection.commit();
    }

    public static void close() throws SQLException {
        dbConnection.close();
    }

    public static void initialiseTable(Statement stmt) throws SQLException {
        String createTableQuery;
        ResultSet rs;
        try {
            rs = dbConnection.getMetaData().getTables(null, null, "DEMO", null);
            if (!rs.next()) {
                createTableQuery
                        = "CREATE TABLE Demo ("
                        + "user_id INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                        + "username VARCHAR(15) NOT NULL UNIQUE,"
                        + "password VARCHAR(15) NOT NULL)";
                stmt.executeUpdate(createTableQuery);
                System.out.println("Table 'Demo' created successfully.");
                commit();

                String insertValuesQuery = "INSERT INTO Demo (username, password) "
                        + "VALUES "
                        + "('dcoms', 'tutorial')";
                stmt.executeUpdate(insertValuesQuery);
                System.out.println("Static values added to 'Demo' table.");
                commit();
            } else {
                System.out.println("Table 'Demo' already exists. No new table created.");
            }
        } catch (SQLException ex) {
            System.out.println("Error creating/checking table: " + ex.getMessage());
        }
    }

}
