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
        String sql = "INSERT INTO services(name, price) VALUES(?, ?)";
        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, service.getName());
            st.setDouble(2, service.getPrice());
            st.executeUpdate();
            return true;
        } catch (SQLException e) { return false; }
    }

    @Override
    public List<Service> getAllServices() {
        List<Service> services = new ArrayList<>();
        try (Connection con = db.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM services")) {
            while (rs.next()) {
                services.add(new Service(rs.getInt("id"), rs.getString("service_name"), rs.getDouble("price")));
            }
        } catch (SQLException e) { System.out.println(e.getMessage()); }
        return services;
    }
}
