package repository;

import config.interfaces.IDB;
import entity.Appointment;
import entity.AppointmentDetailsDTO;
import repository.interfaces.IAppointmentRepository;
import config.PostgresDB;  


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentRepository implements IAppointmentRepository {

    private final IDB db;

    public AppointmentRepository(IDB db) {
        this.db = db;
    }

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

        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public List<String> getAllAppointments() {
        List<String> list = new ArrayList<>();

        String sql = """
                    SELECT a.id,
                           u.name,
                           u.surname,
                           s.service_name,
                           t.slot_time
                    FROM appointments a
                    JOIN users u ON a.user_id = u.id
                    JOIN services s ON a.service_id = s.id
                    JOIN timeslots t ON a.slot_id = t.id
                """;

        try (Connection con = db.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(
                        "Appointment #" + rs.getInt("id") +
                                ": " + rs.getString("name") + " " + rs.getString("surname") +
                                " | " + rs.getString("service_name") +
                                " | " + rs.getString("slot_time")
                );
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return list;
    }

    @Override
    public AppointmentDetailsDTO getFullAppointment(int appointmentId) {
        String sql = """
                    SELECT a.id,
                           u.name,
                           u.surname,
                           s.service_name,
                           t.slot_time
                    FROM appointments a
                    JOIN users u ON a.user_id = u.id
                    JOIN services s ON a.service_id = s.id
                    JOIN timeslots t ON a.slot_id = t.id
                    WHERE a.id = ?
                """;

        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setInt(1, appointmentId);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                AppointmentDetailsDTO dto = new AppointmentDetailsDTO();
                dto.setAppointmentId(rs.getInt("id"));
                dto.setUserName(rs.getString("name") + " " + rs.getString("surname"));
                dto.setServiceName(rs.getString("service_name"));
                dto.setSlotTime(rs.getString("slot_time"));
                return dto;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

}


