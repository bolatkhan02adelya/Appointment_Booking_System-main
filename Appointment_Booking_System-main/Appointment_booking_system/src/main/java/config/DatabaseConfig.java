package config;

import config.interfaces.IDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

<<<<<<< HEAD
public class DatabaseConfig {
    private static final String URL = "jdbc:postgresql://localhost:5432/Appointments";
    private static final String USER = "postgres";
    private static final String PASSWORD = "02012008";
=======
public class DatabaseConfig implements IDB {
>>>>>>> 51a01ada8f1fc92b89555c685cf26493baf91906


    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void main(String[] args) {
        DatabaseConfig config = new DatabaseConfig();

        try (Connection conn = config.getConnection()) {
            if (conn != null) {
                System.out.println("Соединение с базой данных успешно!");
            }
        } catch (SQLException e) {
            System.out.println("Ошибка подключения: " + e.getMessage());
        }
    }
}