package repository;

import config.interfaces.IDB;
import entity.Service;
import repository.interfaces.IServiceRepository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceRepository implements IServiceRepository {
    private final IDB db;
    public ServiceRepository(IDB db) { this.db = db; }

    @Override
    public boolean createService(Service service) {
        String sql = "INSERT INTO services(service_name, price, category_id) VALUES(?, ?, ?)";
        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, service.getName());
            st.setDouble(2, service.getPrice());
            st.setNull(3, Types.INTEGER);
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error creating service: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Service> getAllServices() {
        List<Service> services = new ArrayList<>();
        String sql = "SELECT s.id, s.service_name, s.price, c.name AS cat_name " +
                "FROM services s " +
                "LEFT JOIN categories c ON s.category_id = c.id";

        try (Connection con = db.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                String category = rs.getString("cat_name");
                String displayName = rs.getString("service_name") + (category != null ? " [" + category + "]" : "");

                services.add(new Service(
                        rs.getInt("id"),
                        displayName,
                        rs.getDouble("price")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error getting services: " + e.getMessage());
        }
        return services;
    }
}