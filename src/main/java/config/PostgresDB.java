package config;

import config.interfaces.IDB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresDB implements IDB {
    private static PostgresDB instance;

    private PostgresDB() {}

    public static synchronized PostgresDB getInstance() {
        if (instance == null) {
            instance = new PostgresDB();
        }
        return instance;
    }

    @Override
    public Connection getConnection() throws SQLException {
        String connectionUrl = "jdbc:postgresql://localhost:5432/appointment_booking_system";
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(connectionUrl, "postgres", "password");
        } catch (Exception e) {
            System.out.println("failed to connect to postgres: " + e.getMessage());
            throw new SQLException(e);
        }
    }
}