package controller.interfaces;
import entity.TimeSlot;
import java.util.List;

public interface IBookingController {
    String bookAppointment(int userId, int serviceId, int slotId);
    List<TimeSlot> getAvailableSlots();
}
