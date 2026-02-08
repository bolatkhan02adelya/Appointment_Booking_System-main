package service.interfaces;

import entity.TimeSlot;
import java.util.List;

public interface IAppointmentService {
    List<String> getAllAppointments();
    String book(int userId, int serviceId, int slotId);
    List<TimeSlot> getAvailableSlots();
}
