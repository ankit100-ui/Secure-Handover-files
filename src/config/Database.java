package config;

import java.sql.*;

public final class Database {
    private static final String URL =
        "jdbc:mysql://localhost:3306/secure_handover?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";

    private static final String USER = "root";
    private static final String PASSWORD = "Secure2027"; // CHANGE THIS if needed

    private Database() {}

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
