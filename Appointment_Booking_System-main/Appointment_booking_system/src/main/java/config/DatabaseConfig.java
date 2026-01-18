package config;

import config.interfaces.IDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig implements IDB {

    private static final String URL = "jdbc:postgresql://localhost:5432/appointment_db";
    private static final String USER = "postgres";      // твой username
    private static final String PASSWORD = "password";  // твой пароль

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void main(String[] args) {
        try (Connection conn = getConnection()) {
            if (conn != null) {
                System.out.println("Соединение с базой данных успешно!");
            }
        } catch (SQLException e) {
            System.out.println("Ошибка подключения: " + e.getMessage());
        }
    }
}
