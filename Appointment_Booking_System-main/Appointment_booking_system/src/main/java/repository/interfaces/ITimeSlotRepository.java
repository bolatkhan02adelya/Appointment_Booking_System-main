package repository.interfaces;

import entity.TimeSlot;
import java.util.List;

public interface ITimeSlotRepository {
    List<TimeSlot> getAllSlots();
    boolean createTimeSlot(TimeSlot slot);
}
