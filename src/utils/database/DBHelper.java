package utils.database;

import java.sql.*;

public class DBHelper {
    private static final String username = "root";
    private static final String password = "root";
    private static final String url = "jdbc:mysql://localhost:3306/kahoot?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC";
    private static Connection connection;

    public DBHelper() throws SQLException {
        connection = DriverManager.getConnection(url, username, password);
    }

    public Statement getStatement() throws SQLException {
        return connection.createStatement();
    }

    public PreparedStatement getPreparedStatement(String query) throws  SQLException {
        return connection.prepareStatement(query);
    }

    public PreparedStatement getPreparedStatement(String query, int option) throws  SQLException {
        return connection.prepareStatement(query, option);
    }
}
