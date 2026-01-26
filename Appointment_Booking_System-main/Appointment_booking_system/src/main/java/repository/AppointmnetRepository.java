package repository;

import config.interfaces.IDB;
import entity.Appointment;
import repository.interfaces.IAppointmentRepository;
import java.sql.*;

public class AppointmentRepository implements IAppointmentRepository {
    private final IDB db;
    public AppointmentRepository(IDB db) { this.db = db; }

    @Override
    public boolean makeAppointment(Appointment app) {
        String sql = "INSERT INTO appointments(user_id, service_id, slot_id) VALUES(?, ?, ?)";
        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {
            st.setInt(1, app.getUserId());
            st.setInt(2, app.getServiceId());
            st.setInt(3, app.getSlotId());
            st.executeUpdate();
            return true;
        } catch (SQLException e) { return false; }
    }
}
