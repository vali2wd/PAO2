package util;

import java.sql.*;

public class DatabaseManager {
    private static Connection conn;

    public static Connection getConnection(String jdbcUrl) throws SQLException {
        if (conn == null) {
            conn = DriverManager.getConnection(jdbcUrl);
        }
        return conn;
    }

    public static void closeConnection() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }
}

