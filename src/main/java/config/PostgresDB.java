package config;

import config.interfaces.IDB;

import java.sql.Connection;
import java.sql.DriverManager;

public class PostgresDB implements IDB {

    private static PostgresDB instance;


    private PostgresDB() {
    }


    public static PostgresDB getInstance() {
        if (instance == null) {
            instance = new PostgresDB();
        }
        return instance;
    }

    @Override
    public Connection getConnection() {
        String connectionUrl = "jdbc:postgresql://localhost:5432/Appointment_booking_system";
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(connectionUrl, "postgres", "password");
        } catch (Exception e) {
            System.out.println("failed to connect to postgres: " + e.getMessage());
            return null;
        }
    }
}