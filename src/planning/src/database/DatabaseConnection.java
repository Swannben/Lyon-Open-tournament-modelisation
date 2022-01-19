package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;


/* Exemple {
    DatabaseConnection connection = DatabaseConnection.create(login, password);

    connection.execute("SELECT * FROM arbitre");

    connection.close();
} */

public class DatabaseConnection {
    
    public static DatabaseConnection create(String login, String password) {
        try {
            instance = new DatabaseConnection(login, password);
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return instance;
    }
    
    public static DatabaseConnection get() {
        return instance;
    }
    
    public void close() {
        try {
            statement.close();
            connection.close();
            instance = null;
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    
    public Statement getStatement() {
        return statement;
    }
    
    
    
    private DatabaseConnection(String login, String password) throws SQLException {
        final String database = "jdbc:postgresql://ec2-54-195-141-170.eu-west-1.compute.amazonaws.com:5432/d1ddigt1k2ilv5";
        connection = DriverManager.getConnection(database + "?sslmode=require&user=" + login + "&password=" + password);
        statement = connection.createStatement();
    }
        
    
    private Connection connection;
    private Statement statement;
    
    private static DatabaseConnection instance; 
}
