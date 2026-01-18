package service;

import entity.Appointment;
import entity.TimeSlot;
import repository.interfaces.IAppointmentRepository;
import repository.interfaces.ITimeSlotRepository;
import java.util.List;

public class AppointmentService {

    private final IAppointmentRepository appointmentRepo;
    private final ITimeSlotRepository slotRepo;

    public AppointmentService(IAppointmentRepository appointmentRepo, ITimeSlotRepository slotRepo) {
        this.appointmentRepo = appointmentRepo;
        this.slotRepo = slotRepo;
    }

    public String book(int userId, int serviceId, int slotId) {
        List<TimeSlot> allSlots = slotRepo.getAllSlots();
        TimeSlot selectedSlot = null;

        for (TimeSlot s : allSlots) {
            if (s.getId() == slotId) {
                selectedSlot = s;
                break;
            }
        }

        if (selectedSlot == null) {
            return "Error: Slot not found!";
        }

        Appointment appointment = new Appointment(userId, serviceId, slotId);
        boolean isSaved = appointmentRepo.makeAppointment(appointment);

        if (isSaved) {
            return "Booking successful!!";
        } else {
            return "Error: Slot is already occupied or database error.";
        }
    }

    public List<TimeSlot> getAvailableSlots() {
        return slotRepo.getAllSlots();
    }
}
