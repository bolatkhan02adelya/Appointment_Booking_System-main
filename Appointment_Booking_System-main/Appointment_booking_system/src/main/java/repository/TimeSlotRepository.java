package repository;

import config.interfaces.IDB;
import entity.TimeSlot;
import repository.interfaces.ITimeSlotRepository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TimeSlotRepository implements ITimeSlotRepository {
    private final IDB db;

    public TimeSlotRepository(IDB db) {
        this.db = db;
    }

    @Override
    public List<TimeSlot> getAllSlots() {
        List<TimeSlot> slots = new ArrayList<>();
        String sql = "SELECT id, slot_time, is_available FROM timeslots";

        try (Connection con = db.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                slots.add(new TimeSlot(
                        rs.getInt("id"),
                        rs.getString("slot_time"),
                        rs.getBoolean("is_available")
                ));
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return slots;
    }

    @Override
    public boolean createTimeSlot(TimeSlot slot) {
        String sql = "INSERT INTO timeslots(slot_time, is_available) VALUES(?, ?)";
        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, slot.getSlotTime());
            st.setBoolean(2, slot.isAvailable());
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Save Error: " + e.getMessage());
            return false;
        }
    }
}
