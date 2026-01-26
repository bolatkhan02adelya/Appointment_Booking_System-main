package repository;

import config.interfaces.IDB;
import entity.TimeSlot;
import repository.interfaces.ITimeSlotRepository;
import java.sql.*;
import java.time.LocalTime;
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
        // Убрали is_available из запроса
        String sql = "SELECT id, slotTime FROM timeslots";

        try (Connection con = db.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                LocalTime time = rs.getTime("slot_time").toLocalTime();
                // Создаем объект только с ID и временем
                slots.add(new TimeSlot(rs.getInt("id"), time));
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return slots;
    }
}
