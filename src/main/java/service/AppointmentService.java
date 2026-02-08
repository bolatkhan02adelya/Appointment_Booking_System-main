package service;

import entity.Appointment;
import entity.TimeSlot;
import repository.interfaces.IAppointmentRepository;
import repository.interfaces.ITimeSlotRepository;
import java.util.List;
import entity.AppointmentDetailsDTO;

public class AppointmentService {

    private final IAppointmentRepository appointmentRepo;
    private final ITimeSlotRepository slotRepo;

    public AppointmentService(IAppointmentRepository appointmentRepo, ITimeSlotRepository slotRepo) {
        this.appointmentRepo = appointmentRepo;
        this.slotRepo = slotRepo;
    }

    public List<String> getAllAppointments() {
        return appointmentRepo.getAllAppointments();
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


        if (!selectedSlot.isAvailable()) {
            return "Error: Slot is already occupied!";
        }

        Appointment appointment = new Appointment(userId, serviceId, slotId);
        boolean isSaved = appointmentRepo.makeAppointment(appointment);

        if (isSaved) {
            selectedSlot.setAvailable(false);
            return "Booking successful!";
        }

        if (userId <= 0 || serviceId <= 0 || slotId <= 0) {
            return "Validation Error: Negative or zero IDs are not allowed!";
        }

        return "Database error.";
    }

    public List<TimeSlot> getAvailableSlots() {
        List<TimeSlot> allSlots = slotRepo.getAllSlots();
        allSlots.removeIf(slot -> !slot.isAvailable());
        return allSlots;
    }
    public AppointmentDetailsDTO getFullAppointment(int appointmentId) {
        return appointmentRepo.getFullAppointment(appointmentId);
    }
}
